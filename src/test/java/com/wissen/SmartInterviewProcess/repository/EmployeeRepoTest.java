package com.wissen.SmartInterviewProcess.repository;

import java.awt.print.Pageable;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.models.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepoTest {

	
	
	@Autowired
	EmployeeRepository emprepo;
	
//	@Test
	@Transactional
	public void addEmployee() {
		Employee emp = new Employee();
		emp.setEmail("sadfsdf@sdfg.fg");
		emp.setName("sagar");
		emp.setPhoneNumber("888888888888");
		emp.setWissenId("WT455");
		
		emprepo.save(emp);
	}
	
//	@Test
	public void findAllByActive() {
		System.out.println(emprepo.findByActive(true,PageRequest.of(0, 10)));
		System.out.println(emprepo.findByActive(false,PageRequest.of(0, 10)));

		
//		System.out.println(empRep);
		
	}
	
	
	
	
}
