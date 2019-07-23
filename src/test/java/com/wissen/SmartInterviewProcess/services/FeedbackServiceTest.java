package com.wissen.SmartInterviewProcess.services;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.models.Status;

import javassist.NotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeedbackServiceTest {

	@Autowired
	FeedbackService feedbackService;
	
	@Test
	@Transactional
	public void addFeedbackTest() throws NotFoundException {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		
		feedbackDTO.setFeedback("TEST feedback");
		feedbackDTO.setScheduleId(4L);
		feedbackDTO.setStatus(Status.ACCEPTED);
		
		assertNotNull(feedbackService.addFeedback(feedbackDTO));
	}
	
	@Test
	@Transactional
	public void viewFeedbackTest() throws NotFoundException {
		LocalDateTime from = LocalDateTime.now();
		LocalDateTime to = LocalDateTime.now().plusDays(1);
		
		assertNotNull(feedbackService.viewFeedback(1L, from, to));
	}

}
