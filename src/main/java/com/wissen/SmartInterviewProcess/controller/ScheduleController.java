package com.wissen.SmartInterviewProcess.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.services.ScheduleSlotService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class ScheduleController {

	@Autowired
	ScheduleSlotService scheduleSlotService;
	
	@PostMapping("/hrs/{id}/schedules")
	private ResponseEntity<?> addSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDTO scheduleSlotDTO) {
		System.out.println(scheduleSlotDTO);
		ScheduleResponseDTO body = null;
		try {
			body = scheduleSlotService.scheduleInterview(scheduleSlotDTO);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(body, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/hrs/{id}/schedules")
	private ResponseEntity<?> viewScheduleHr(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
		if(from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}
		
		List<ScheduleResponseDTO> body;
		try {
			body = scheduleSlotService.getScheduleBetweenForHr(id, LocalDateTime.parse(from), LocalDateTime.parse(to));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@GetMapping("/interviewers/{id}/schedules")
	private ResponseEntity<?> viewScheduleInterviewer(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
		if(from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}
		
		List<ScheduleResponseDTO> body;
		try {
			body = scheduleSlotService.getScheduleBetweenForInterviewer(id, LocalDateTime.parse(from), LocalDateTime.parse(to));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
