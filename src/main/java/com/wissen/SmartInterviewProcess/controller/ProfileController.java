package com.wissen.SmartInterviewProcess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.services.ProfileService;

@RestController
@RequestMapping("/api")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@PutMapping("/interviewers/{id}")
	private ResponseEntity<?> editPreferences(@RequestParam Long id, @RequestBody InterviewerDTO interviewerDTO) {
		Interviewer responseBody = profileService.editPreferences(id, interviewerDTO);
		
		return new ResponseEntity<Interviewer>(responseBody, HttpStatus.OK);
	}

}
