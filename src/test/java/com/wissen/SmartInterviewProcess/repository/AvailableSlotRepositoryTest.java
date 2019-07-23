package com.wissen.SmartInterviewProcess.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.models.AvailableSlot;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AvailableSlotRepositoryTest {

	@Autowired
	AvailableSlotRepository availableSlotRepository;

	
	@Autowired
	InterviewerRepository interviewRepository;
	
//	@Test
//	@Transactional
//	public void findAllAvailableTest() {
//		assertNotNull(availableSlotRepository.findAll());
//	}
//
//	
//	@Test
//	@Transactional
//	public void findAllInterviewersTest() {
//		assertNotNull(interviewRepository.findAll());
//	}
	
	
	@Test
	@Transactional
	public void findAllBetweenRangeTest() {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		assertNotNull(availableSlotRepository.getAllBetween(from, to,true));
	}
	
	@Test
	@Transactional
	public void getAllBetweenByInterviewerTest() {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		assertNotNull(availableSlotRepository.getAllBetweenByInterviewer(from, to, 1, true));
	}
	
//	@Test
//	public void findAllBetweenRangeCutToCutFromOrTo() {
//
//		LocalDateTime from = LocalDateTime.of(2019,7,16,5,0,0);
//		LocalDateTime to = LocalDateTime.of(2019,7,16,8,0,0);
//		System.out.println("getting recors between date: "+from);
//		repo.getAllBetween(from, to,true).forEach(e->{
//			
//			System.out.println(e);
//		});
//		System.out.println("getting recors between date: "+to);
//	}
	
	@Test
	@Transactional
	public void findAllByActive() {
		assertNotNull((availableSlotRepository.findByActiveTrue()));
	}
	
//	@Test
//	@Transactional
//	public void addAvailableSlot() {
//		
//		AvailableSlot slot = new AvailableSlot();
//		
//		
//		slot.setFromTimestamp(LocalDateTime.now().plusDays(10));
//		
//		slot.setToTimestamp(LocalDateTime.now().plusDays(15));
//		
//		slot.setInterviewer(interviewRepository.findByWissenId("WT250"));
//		
//		assertTrue(availableSlotRepository.save(slot) instanceof AvailableSlot);
//		
//	}
	
}
