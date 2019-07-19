package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.dto.SlotDTO;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AvailableSlotServiceTest {

	@Autowired
	AvailableSlotService service;
	
	
//	@Test
	public void getAllBetween() {
		
		
		System.out.println(service.getSlotsBetween(LocalDateTime.now().minusHours(12), LocalDateTime.now()));
		
	}
//	@Test
	public void getAllBetweenForInterviewer() {
		
		
		try {
			System.out.println(service.getSlotsBetween(1L,LocalDateTime.now().minusHours(12), LocalDateTime.now()));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void addAvailableSlots() {
		
		List<SlotDTO> slots = new ArrayList<>();
		
		for(int i=1 ; i<=3 ; i++) {
			slots.add(new SlotDTO(LocalDateTime.now().plusDays(i), LocalDateTime.now().plusDays(i).plusMinutes(30)));
		}
		
		
		try {
			service.addSlots(1L,slots);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
