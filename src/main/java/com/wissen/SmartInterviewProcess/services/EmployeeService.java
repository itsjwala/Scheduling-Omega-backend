package com.wissen.SmartInterviewProcess.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	InterviewerRepository interviewerRepository;
	
	@Transactional
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		
		employee.setActive(true);
		employee.setName(employeeDTO.getName());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setEmail(employeeDTO.getEmail());
		employee.setWissenId(employeeDTO.getWissenId());
		
		Employee newEmployee = employeeRepository.save(employee);
		
		return newEmployee;
	}
	
	@Transactional
	public List<Employee> allEmployees() {
		return employeeRepository.findAll();
	}
}
