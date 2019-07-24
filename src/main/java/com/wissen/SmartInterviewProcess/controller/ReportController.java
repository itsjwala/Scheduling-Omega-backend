package com.wissen.SmartInterviewProcess.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.repository.ReportRepository;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {
	
	@Autowired 
	ReportRepository reportRepository;
	
	@GetMapping("/interviewers")
	public List<?> monthlyInterviewerReport(@RequestParam String from, @RequestParam String to) {
	
		return reportRepository.interviewerReport(LocalDateTime.parse(from), LocalDateTime.parse(to));
	}
	
	@GetMapping("/hrs")
	public List<?> monthlyHrReport(@RequestParam String from, @RequestParam String to) {
		
		return reportRepository.hrReport(LocalDateTime.parse(from), LocalDateTime.parse(to));
	}
	
	@GetMapping("/interviewers/no-slots")
	public List<?> noSlotsGivenReport(@RequestParam String from, @RequestParam String to) {
		return reportRepository.noSlotsGiven(LocalDateTime.parse(from), LocalDateTime.parse(to));
	}
	
}
