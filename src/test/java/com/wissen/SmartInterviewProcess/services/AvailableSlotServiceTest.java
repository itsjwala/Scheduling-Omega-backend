package com.wissen.SmartInterviewProcess.services;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.SlotDTO;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AvailableSlotServiceTest {

	@Autowired
	AvailableSlotService availableSlotService;
	
	@Test
	@Transactional
	public void addSlotsTest() throws NotFoundException {
		List<SlotDTO> slots = new ArrayList<>();
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusMinutes(30);
		slots.add(new SlotDTO(from, to));
		
		from = LocalDateTime.now().plusDays(1);
		to = LocalDateTime.now().plusDays(1).plusMinutes(30);
		slots.add(new SlotDTO(from, to));
		
		assertNotNull(availableSlotService.addSlots(1L, slots));
	}
	
	@Test
	@Transactional
	public void getSlotsBetweenTest() {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		
		assertNotNull(availableSlotService.getSlotsBetween(from, to));
	}
	
	@Test
	@Transactional
	public void getSlotsBetweenInterviewerTest() throws NotFoundException {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		
		assertNotNull(availableSlotService.getSlotsBetween(1L, from, to));
	}
	
	
	
	
	
}
