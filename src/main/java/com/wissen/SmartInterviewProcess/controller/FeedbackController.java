package com.wissen.SmartInterviewProcess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleFeedbackDTO;
import com.wissen.SmartInterviewProcess.services.FeedbackService;
import com.wissen.SmartInterviewProcess.services.MailService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/interviewers")
@CrossOrigin(origins = "*")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	MailService mailService;
	
	@PostMapping("/{id}/schedules/feedback")
	private ResponseEntity<?> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		Long body = -1L;
		
		try {
			body = feedbackService.addFeedback(feedbackDTO);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
//	@GetMapping("/{id}/schedules/feedback")
//	private ResponseEntity<?> viewFeedback(@PathVariable Long id, @RequestParam String from, @RequestParam String to) {
//		List<FeedbackDTO> body;
//		try {
//			body = feedbackService.viewFeedback(id, LocalDateTime.parse(from), LocalDateTime.parse(to));
//		} catch (NotFoundException e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<>(body, HttpStatus.OK);
//	}
	@GetMapping("/{id}/schedules/feedback")
	private ResponseEntity<?> viewFeedback(@PathVariable Long id) {
		List<ScheduleFeedbackDTO> body;
		try {
			body = feedbackService.viewFeedbackInterviewer(id);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
