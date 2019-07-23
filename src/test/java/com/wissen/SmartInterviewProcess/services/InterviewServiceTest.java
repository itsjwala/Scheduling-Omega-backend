package com.wissen.SmartInterviewProcess.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InterviewServiceTest {

	@Autowired
	InterviewService interviewService;
	
	@Test
	@Transactional
	public void addInterviewerTest() {
		InterviewerDTO interviewerDTO = new InterviewerDTO();
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail("test@email.com");
		employeeDTO.setName("TEST LAST");
		employeeDTO.setPhoneNumber("91921");
		employeeDTO.setWissenId("Wt456");
		interviewerDTO.setEmp(employeeDTO);
		
		List<Level> levels = new ArrayList<>();
		Level newLevel = new Level();
		newLevel.setId(1L);
		newLevel.setLevel("R1");
		levels.add(newLevel);
		interviewerDTO.setLevels(levels);
		
		List<Technology> technologies = new ArrayList<>();
		Technology technology = new Technology();
		technology.setId(1L);
		technology.setTechnology("Angular");
		
		interviewerDTO.setLevels(levels);
		interviewerDTO.setTechnologies(technologies);
		
		assertNotNull(interviewService.addInterviewer(interviewerDTO));
	}
	
	@Test
	@Transactional
	public void allInterviewersTest() {
		assertNotNull(interviewService.allInterviewers());
	}
}
