package com.wissen.SmartInterviewProcess.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.SmartInterviewProcess.repository.ReportRepository;
import com.wissen.SmartInterviewProcess.repository.ReportRepository.HrReportDTO;
import com.wissen.SmartInterviewProcess.repository.ReportRepository.InterviewerReportDTO;
import com.wissen.SmartInterviewProcess.repository.ReportRepository.NoSlotDTO;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {
	
	@Autowired 
	ReportRepository reportRepository;
	
	@GetMapping("/interviewers")
	public ResponseEntity<?> monthlyInterviewerReport(@RequestParam String from, @RequestParam String to) {
	
		List<InterviewerReportDTO> responseBody = reportRepository.interviewerReport(LocalDateTime.parse(from), LocalDateTime.parse(to));
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
	
	@GetMapping("/hrs")
	public ResponseEntity<?> monthlyHrReport(@RequestParam String from, @RequestParam String to) {
		
		List<HrReportDTO> responseBody = reportRepository.hrReport(LocalDateTime.parse(from), LocalDateTime.parse(to));
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
	
	@GetMapping("/interviewers/no-slots")
	public ResponseEntity<?> noSlotsGivenReport(@RequestParam String from, @RequestParam String to) {
		
		List<NoSlotDTO> responseBody = reportRepository.noSlotsGiven(LocalDateTime.parse(from), LocalDateTime.parse(to));
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
		
	}
	
}
