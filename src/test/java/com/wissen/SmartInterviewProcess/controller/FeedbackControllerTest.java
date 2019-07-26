package com.wissen.SmartInterviewProcess.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.models.Status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class FeedbackControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Transactional
	public void viewFeedbackTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/interviewers/{id}/schedules/feedback", "1")
				.param("from", "2019-06-19T09:30:53.433")
				.param("to", "2019-08-20T09:30:53.433"))
				.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void addFeedbackTest() throws Exception {
		FeedbackDTO feedbackDTO = new FeedbackDTO();
		feedbackDTO.setScheduleId(1L);
		feedbackDTO.setFeedback("TEST");
		feedbackDTO.setStatus(Status.ACCEPTED);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/interviewers/{id}/schedules/feedback", "1")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(asJsonString(feedbackDTO)))
				.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
