package com.wissen.SmartInterviewProcess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.services.InterviewService;
import com.wissen.SmartInterviewProcess.services.MailService;

@RestController
@RequestMapping("/api/employees/interviewers")
@CrossOrigin(origins = "*")
public class InterviewerController {

	@Autowired
	InterviewService interviewService;
	
	@Autowired
	MailService mailService;
	
	
	@PostMapping
	private ResponseEntity<Interviewer> addInterviewer(@RequestBody InterviewerDTO interviewerDTO) {
		Interviewer newInterviewer = interviewService.addInterviewer(interviewerDTO);
		
		String mailBody = "New interviewer " + interviewerDTO.getEmp().getName() + " with Wissen ID " + interviewerDTO.getEmp().getWissenId() + " has registered.";
		mailService.sendMail(interviewerDTO.getEmp().getEmail(), "New Employee Registered", mailBody);
		
		return new ResponseEntity<Interviewer>(newInterviewer, HttpStatus.OK);
	}
	
	@GetMapping
	private ResponseEntity<List<Interviewer>> allInterviewers() {
		return new ResponseEntity<List<Interviewer>>(interviewService.allInterviewers(), HttpStatus.OK);
	}
}
