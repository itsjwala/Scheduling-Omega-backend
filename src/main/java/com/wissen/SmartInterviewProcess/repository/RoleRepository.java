package com.wissen.SmartInterviewProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.SmartInterviewProcess.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	public Role findByRole(String role);

}
