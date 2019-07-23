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
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.services.EmployeeService;
import com.wissen.SmartInterviewProcess.services.MailService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	MailService mailService;
	
	@PostMapping
	private ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
		
		String mailBody = "New employee " + employeeDTO.getName() + " with Wissen ID " + employeeDTO.getWissenId() + " has registered.";
		mailService.sendMail(employeeDTO.getEmail(), "New Employee Registered", mailBody);
		
		String responseBody = "{\"token\": \"jashashdn1.1212h2haa.12312sdfsdfds\"}";
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<Employee>> allEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.allEmployees(), HttpStatus.OK);
	}

	
}
