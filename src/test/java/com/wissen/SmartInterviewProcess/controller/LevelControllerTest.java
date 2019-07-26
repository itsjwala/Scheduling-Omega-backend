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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LevelControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void allLevelsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/levels"))
				.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void addLevelsTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/levels").content("R3"))
					.andExpect(status().isOk())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
	}
	

}
