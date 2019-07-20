package com.wissen.SmartInterviewProcess.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.AvailableSlotDTO;
import com.wissen.SmartInterviewProcess.dto.AvailableSlotForScheduleDTO;
import com.wissen.SmartInterviewProcess.dto.SlotDTO;
import com.wissen.SmartInterviewProcess.services.AvailableSlotService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/interviewers")
public class SlotController {

	@Autowired
	AvailableSlotService  availableSlotService;
	
	@PostMapping("/{id}/slots")
	private ResponseEntity<?> addSlots(@PathVariable Long id, @RequestBody List<SlotDTO> availableSlots) {
		
		List<AvailableSlotDTO> body;
		try {
			body = availableSlotService.addSlots(id, availableSlots);
		} catch (NotFoundException e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<AvailableSlotDTO>>(body, HttpStatus.OK);
	}
	
	@GetMapping("/slots")
	private ResponseEntity<?> viewSlots(@RequestParam String from, @RequestParam String to) {
		
		if(from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}
		
		List<AvailableSlotForScheduleDTO> body = availableSlotService.getSlotsBetween(LocalDateTime.parse(from), LocalDateTime.parse(to));
		
		return new ResponseEntity<List<AvailableSlotForScheduleDTO>>(body, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/slots/{slotId}")
	private ResponseEntity<?> cancelSlot(@PathVariable Long slotId ) {
		Long updatedSlot = -1L;
		try {
			updatedSlot = availableSlotService.cancelSlot(slotId);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(updatedSlot, HttpStatus.OK);
				
	}
	
	@GetMapping("/{id}/slots")
	private ResponseEntity<?> interviewerSlots(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
		
		if(from.equals("undefined") || to.equals("undefined") || from.length() == 0 || to.length() == 0) {
			return new ResponseEntity<>("Invalid time range", HttpStatus.BAD_REQUEST);
		}
		
		List<AvailableSlotDTO> body;
		try {
			body = availableSlotService.getSlotsBetween(id, LocalDateTime.parse(from), LocalDateTime.parse(to));
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
}
