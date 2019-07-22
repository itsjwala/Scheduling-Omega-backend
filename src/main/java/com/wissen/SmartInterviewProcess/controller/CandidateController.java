package com.wissen.SmartInterviewProcess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.models.Candidate;
import com.wissen.SmartInterviewProcess.repository.CandidateRepository;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {
	
	@Autowired
	CandidateRepository candidateRepository;

	@GetMapping
	private ResponseEntity<List<Candidate>> allCandidates() {
		return new ResponseEntity<List<Candidate>>(candidateRepository.findAll(), HttpStatus.OK);
	}
}
