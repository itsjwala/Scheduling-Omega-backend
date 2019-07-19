package com.wissen.SmartInterviewProcess.repository;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.models.ScheduleSlot;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduledRepositoryTest {

	@Autowired
	ScheduleSlotRepository repo;
	
	@Autowired
	CandidateRepository candidateRepo;
	
	@Autowired
	InterviewerRepository inteRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	AvailableSlotRepository slotRepo;
	
//	@Test
	public void getAllByInterviewer() {
		
		System.out.println(repo.getAllByInterviewer(1, false,true));

	}
	
//	@Test
	public void getAllBetweenByInterviewer() {
		
		System.out.println(repo.getAllBetweenByInterviewer(1, LocalDateTime.now().minusDays(1),LocalDateTime.now(), false, true));
	}

//	@Test
	public void getAllByHr() {
		System.out.println(repo.getAllByHr(5, false,true));
	}
	@Test
	@Transactional
	public void addTest() {
		ScheduleSlot slot=new ScheduleSlot();
		
		slot.setCandidate(candidateRepo.getOne(1L));
		
		
		slot.setHr(empRepo.getOne(5L));
		
		slot.setSlot(slotRepo.getOne(7L));
		
		slot.setInterviewer(inteRepo.getOne(1L));
		
		slot.setInterviewDescription("room 314 java interview for round 1");
		
		repo.save(slot);
		
		
		
	}
	
}
