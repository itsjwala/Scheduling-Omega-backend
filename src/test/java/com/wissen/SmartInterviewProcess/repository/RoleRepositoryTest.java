package com.wissen.SmartInterviewProcess.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wissen.SmartInterviewProcess.models.Role;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleRepositoryTest {

	@Autowired
	RoleRepository roleRepository;
	
	@Test
	@Transactional
	public void getRoleTest() {
		Role role = roleRepository.findByRole("ADMIN");
		System.out.println(role);
	}
}
