package com.wissen.SmartInterviewProcess.repository;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.models.AvailableSlot;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AvailableSlotRepositoryTest {

	@Autowired
	AvailableSlotRepository repo;

	
	@Autowired
	InterviewerRepository inteRepo;
	
//	@Test
	public void findAll() {
		System.out.println(repo.findAll());
	}

	@Test
	public void findAllBetweenRange() {

		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		System.out.println("getting recors between date: "+from);
		repo.getAllBetween(from, to,true).forEach(e->{
			
			System.out.println(e);
		});
		System.out.println("getting recors between date: "+to);
	}
	
//	@Test
	public void findAllBetweenRangeCutToCutFromOrTo() {

		LocalDateTime from = LocalDateTime.of(2019,7,16,5,0,0);
		LocalDateTime to = LocalDateTime.of(2019,7,16,8,0,0);
		System.out.println("getting recors between date: "+from);
		repo.getAllBetween(from, to,true).forEach(e->{
			
			System.out.println(e);
		});
		System.out.println("getting recors between date: "+to);
	}
	
//	@Test
	public void findAllByActive() {
		System.out.println(repo.findByActiveTrue());
	}
	
	@Test
//	@Transactional
	public void addAvailableSlot() {
		
		AvailableSlot slot = new AvailableSlot();
		
		
		slot.setFromTimestamp(LocalDateTime.now().plusDays(10));
		
		slot.setToTimestamp(LocalDateTime.now().plusDays(15));
		
		slot.setInterviewer(inteRepo.findByWissenId("WT250"));
		
		repo.save(slot);
		
	}
	
}
