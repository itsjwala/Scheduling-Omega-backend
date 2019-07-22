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

import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

@RestController
@RequestMapping("/api/technology")
@CrossOrigin(origins = "*")
public class TechnologyController {

	@Autowired
	TechnologyRepository technologyRepository;
	
	@GetMapping
	private ResponseEntity<List<Technology>> allTechnologies() {
		return new ResponseEntity<List<Technology>>(technologyRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Technology> addTechnology(@RequestBody String technologyName) {
		Technology technology = new Technology();
		technology.setTechnology(technologyName);
		return new ResponseEntity<Technology>(technologyRepository.save(technology), HttpStatus.OK);
	}
	
	
}
