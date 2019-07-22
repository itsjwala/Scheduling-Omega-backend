package com.wissen.SmartInterviewProcess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;

@Controller
@RequestMapping("/api/levels")
@CrossOrigin(origins = "*")
public class LevelController {

	@Autowired
	LevelRepository levelRepository;
	
	@GetMapping
	private ResponseEntity<List<Level>> allLevels() {
		return new ResponseEntity<List<Level>>(levelRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<Level> addLevel(@RequestBody String levelName) {
		Level level = new Level();
		level.setLevel(levelName);
		return new ResponseEntity<Level>(levelRepository.save(level), HttpStatus.OK);
	}
}
