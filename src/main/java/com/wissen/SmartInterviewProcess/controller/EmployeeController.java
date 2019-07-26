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
import com.wissen.SmartInterviewProcess.jwt.JwtAuthenticationController;
import com.wissen.SmartInterviewProcess.jwt.JwtRequest;
import com.wissen.SmartInterviewProcess.jwt.JwtResponse;
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
	
	@Autowired
	JwtAuthenticationController jwtController;
	
	@PostMapping
	private ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
		
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
	ResponseEntity<List<Employee>> allEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.allEmployees(), HttpStatus.OK);
	}

	
}
