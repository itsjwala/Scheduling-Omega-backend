package com.wissen.SmartInterviewProcess.repository;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduleSlotRepositoryTest {
	
	@Autowired
	ScheduleSlotRepository scheduleSlotRepository;
	
	@Test
	@Transactional
	public void getAllByHrTest() {
		assertNotNull(scheduleSlotRepository.getAllByHr(5L, false, true));
	}
	
	@Test
	@Transactional
	public void getAllBetweenByInterviewerTest() {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		assertNotNull(scheduleSlotRepository.getAllBetweenByInterviewer(1L, from, to, false, true));
	}
	
	@Test
	@Transactional
	public void getAllByInterviewerTest() {
		assertNotNull(scheduleSlotRepository.getAllByInterviewer(1L, false, true));
	}
	
	@Test
	@Transactional
	public void getAllBetweenByHrTest() {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		assertNotNull(scheduleSlotRepository.getAllBetweenByHr(5L, from, to, false, true));
	}

}
