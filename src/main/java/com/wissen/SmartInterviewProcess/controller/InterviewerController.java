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

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.jwt.JwtAuthenticationController;
import com.wissen.SmartInterviewProcess.jwt.JwtRequest;
import com.wissen.SmartInterviewProcess.jwt.JwtResponse;
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
	
	@Autowired
	JwtAuthenticationController jwtController;
	
	
	@PostMapping
	private ResponseEntity<?> addInterviewer(@RequestBody InterviewerDTO interviewerDTO) {
		interviewService.addInterviewer(interviewerDTO);
		
		EmployeeDTO employeeDTO = interviewerDTO.getEmp();
		
		JwtRequest req = new JwtRequest();
		req.setUsername(employeeDTO.getEmail());
		req.setPassword(employeeDTO.getPassword());
		
		JwtResponse responseBody = null;
		try {
			responseBody = jwtController.createAuthenticationTokenForRegister(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mailService.sendRegisteringMail(employeeDTO);
		
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
		
	}
	
	@GetMapping
	private ResponseEntity<List<Interviewer>> allInterviewers() {
		return new ResponseEntity<List<Interviewer>>(interviewService.allInterviewers(), HttpStatus.OK);
	}
	
	@GetMapping("/names")
	private ResponseEntity<?> interviewerNames() {
		List<?> responseBody = interviewService.interviewerNames();
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
}
