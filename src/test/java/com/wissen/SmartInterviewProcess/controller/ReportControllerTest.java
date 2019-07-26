package com.wissen.SmartInterviewProcess.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void monthlyInterviewerReportTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/reports/interviewers")
				.param("from", "2019-06-19T09:30:53.433")
				.param("to", "2019-08-20T09:30:53.433")).
				andExpect(status().isOk());
	}

	@Test
	public void monthlyHrReportTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/reports/hrs")
				.param("from", "2019-06-19T09:30:53.433")
				.param("to", "2019-08-20T09:30:53.433"))
				.andExpect(status().isOk());
	}

	@Test
	public void noSlotsGivenReportTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/reports/interviewers/no-slots")
				.param("from", "2019-06-19T09:30:53.433")
				.param("to", "2019-08-20T09:30:53.433"))
				.andExpect(status().isOk());
	}
}
