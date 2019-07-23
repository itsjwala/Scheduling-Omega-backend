package com.wissen.SmartInterviewProcess.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.models.Candidate;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.repository.AvailableSlotRepository;
import com.wissen.SmartInterviewProcess.repository.CandidateRepository;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;
import com.wissen.SmartInterviewProcess.repository.ScheduleSlotRepository;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduleSlotServiceTest {

	@Autowired
	ScheduleSlotService scheduleSlotService;

	@Autowired 
	CandidateRepository candidateRepository;
	
	@Test
	@Transactional
	public void scheduleInterviewTest() throws NotFoundException {
		ScheduleRequestDTO scheduleRequestDTO = new ScheduleRequestDTO();
		
		scheduleRequestDTO.setSlotId(1L);
		scheduleRequestDTO.setHrId(5L);
		scheduleRequestDTO.setInterviewerId(1L);
		scheduleRequestDTO.setLevelId(1L);
		scheduleRequestDTO.setTechnology(1L);
		
		Candidate candidate = candidateRepository.findById(1L).get();
		scheduleRequestDTO.setCandidate(candidate);
		
		assertNotNull(scheduleSlotService.scheduleInterview(scheduleRequestDTO));
	}
	
	
	@Test
	@Transactional
	public void getScheduleHrTest() throws NotFoundException {
		List<ScheduleResponseDTO> res = scheduleSlotService.getScheduleBetweenForHr(5L, LocalDateTime.parse("2019-06-19T09:30:53.433"), LocalDateTime.parse("2019-08-20T09:30:53.433"));
		assertNotNull(res);
	}
	
	@Test
	@Transactional
	public void getScheduleInterviewerTest() throws NotFoundException {
		List<ScheduleResponseDTO> res = scheduleSlotService.getScheduleBetweenForInterviewer(1L, LocalDateTime.parse("2019-06-19T09:30:53.433"), LocalDateTime.parse("2019-08-20T09:30:53.433"));
		assertNotNull(res);
	}
	
}
