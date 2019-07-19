package com.wissen.SmartInterviewProcess.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;

@RunWith(SpringRunner.class)
 @SpringBootTest
public class InterviewerRepoTest {

	@Autowired
	InterviewerRepository repo;

	@Autowired
	LevelRepository levelRepo;
	@Autowired
	TechnologyRepository techRepo;

	@Test
	@Transactional
	public void addTest() {
		Employee emp = new Employee();
		emp.setEmail("sadfsdf@sdfg.fg");
		emp.setName("pavan");
		emp.setPhoneNumber("888888888888");
		emp.setWissenId("WT455");

		Interviewer intee = new Interviewer();

		intee.setLevels(levelRepo.findAll());

		intee.setTechnologies(techRepo.findAll());

		intee.setEmp(emp);

		repo.save(intee);
	}
	
	
//	@Test
	public void viewTest() {

		System.out.println(repo.findAll());
	}

//	@Test
	public void findAllByNameTest() {
		System.out.println(repo.getAllInterviewerName());
	}
}
