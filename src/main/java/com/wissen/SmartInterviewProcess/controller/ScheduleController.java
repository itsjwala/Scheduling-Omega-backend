package com.wissen.SmartInterviewProcess.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.AvailableSlotForScheduleDTO;
import com.wissen.SmartInterviewProcess.dto.CancelDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.services.MailService;
import com.wissen.SmartInterviewProcess.services.ScheduleSlotService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScheduleController {

	@Autowired
	ScheduleSlotService scheduleSlotService;

	@Autowired
	MailService mailService;

	@PostMapping("/hrs/{id}/schedules")
	private ResponseEntity<?> addSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDTO scheduleSlotDTO) {
		System.out.println(scheduleSlotDTO);
		ScheduleResponseDTO responseBody = null;
		try {
			responseBody = scheduleSlotService.scheduleInterview(scheduleSlotDTO);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		String subject = "New Interview Scheduled on" + responseBody.getSlot().getFrom().toString();
		String mailBody = "A new interview has been scheduled on " + responseBody.getSlot().getFrom().toString() + " with " + responseBody.getCandidate().toString();
//		String to[] = scheduleSlotService.mailTo(scheduleSlotDTO.getInterviewerId(), scheduleSlotDTO.getHrId());
//		mailService.sendSchedulingMail(to, subject, mailBody);
		
		// String mailBody = "A new interview has been scheduled on " + responseBody.getSlot().getFrom().toString()
		// 		+ " with " + responseBody.getCandidate().toString();
		// String to[] = scheduleSlotService.mailTo(scheduleSlotDTO.getInterviewerId(), scheduleSlotDTO.getHrId());
		// mailService.sendSchedulingMail(to, subject, mailBody);

		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);

	}

	@GetMapping("/hrs/{id}/schedules")
	private ResponseEntity<?> viewScheduleHr(@PathVariable Long id, @RequestParam String from,
			@RequestParam String to) {
		if (from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}

		List<ScheduleResponseDTO> body;
		try {
			body = scheduleSlotService.getScheduleBetweenForHr(id,
					LocalDateTime.parse(from, DateTimeFormatter.ISO_DATE_TIME),
					LocalDateTime.parse(to, DateTimeFormatter.ISO_DATE_TIME));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@GetMapping("/interviewers/{id}/schedules")
	private ResponseEntity<?> viewScheduleInterviewer(@PathVariable Long id, @RequestParam String from,
			@RequestParam String to) {
		if (from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}

		List<ScheduleResponseDTO> body;
		try {
			body = scheduleSlotService.getScheduleBetweenForInterviewer(id,
					LocalDateTime.parse(from, DateTimeFormatter.ISO_DATE_TIME),
					LocalDateTime.parse(to, DateTimeFormatter.ISO_DATE_TIME));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@DeleteMapping("/interviewers/{id}/schedules/{scheduleId}")
	private ResponseEntity<?> deleteScheduleByInterviewer(@PathVariable("scheduleId") Long scheduleId,
			@RequestBody CancelDTO cancelDTO) {
		if (scheduleId == null)
			return new ResponseEntity<>("Mention a schedule id", HttpStatus.BAD_REQUEST);

		if (cancelDTO == null)
			return new ResponseEntity<>("Mention Cancellation reason", HttpStatus.BAD_REQUEST);
		try {
			scheduleSlotService.cancelScheduleInterviewByInterviewer(scheduleId, cancelDTO.getCancellationReason());
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return ResponseEntity.ok("");
	}

	@DeleteMapping("/hrs/{id}/schedules/{scheduleId}")
	private ResponseEntity<?> deleteScheduleByHr(@PathVariable("scheduleId") Long scheduleId,
			@RequestBody CancelDTO cancelDTO) {
		if (scheduleId == null)
			return new ResponseEntity<>("Mention a schedule id", HttpStatus.BAD_REQUEST);

		
		if (cancelDTO == null)
			return new ResponseEntity<>("Mention Cancellation reason", HttpStatus.BAD_REQUEST);
		AvailableSlotForScheduleDTO responseBody = null;
		try {
			responseBody = scheduleSlotService.cancelScheduleInterviewByHr(scheduleId, cancelDTO.getCancellationReason());
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
<<<<<<< HEAD
		return ResponseEntity.ok(responseBody);
	}	
	
=======
		return ResponseEntity.ok("");
	}
>>>>>>> b32e6878355567511c4fb7d38b3d909b4519b985

}
