package com.wissen.SmartInterviewProcess.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.models.Employee;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;

	@Test
	@Transactional
	public void addEmployeeTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		employeeDTO.setEmail("test@email.com");
		employeeDTO.setName("TEST LAST");
		employeeDTO.setPhoneNumber("91921");
		employeeDTO.setWissenId("Wt456");
		
		Employee savedEmployee = employeeService.addEmployee(employeeDTO);
		
		assertNotNull(savedEmployee);
	}
	
	@Test
	@Transactional
	public void allEmployeesTest() {
		assertNotNull(employeeService.allEmployees());
	}
}
