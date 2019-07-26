package com.wissen.SmartInterviewProcess.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Role;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.RoleRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	InterviewerRepository interviewerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		
		employee.setActive(true);
		employee.setName(employeeDTO.getName());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setEmail(employeeDTO.getEmail());
		employee.setWissenId(employeeDTO.getWissenId());
		
		employee.setPassword(bCryptPasswordEncoder.encode(employeeDTO.getPassword()));
		
		Role role = roleRepository.findByRole(employeeDTO.getRole());
		employee.setRole(role);
		
		Employee newEmployee = employeeRepository.save(employee);
		
		return newEmployee;
	}
	
	@Transactional
	public List<Employee> allEmployees() {
		return employeeRepository.findAll();
	}
}
