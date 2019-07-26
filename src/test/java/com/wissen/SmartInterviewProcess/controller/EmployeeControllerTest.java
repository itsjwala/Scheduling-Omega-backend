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
import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Transactional
	public void addEmployeeController() throws Exception {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmail("test@email.com");
		employeeDTO.setName("TEST");
		employeeDTO.setPassword("pass123");
		employeeDTO.setPhoneNumber("982658");
		employeeDTO.setRole("HR");
		employeeDTO.setWissenId("TMP43");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/employees")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(asJsonString(employeeDTO)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void allEmployeesTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/employees"))
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
