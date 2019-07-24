package com.wissen.SmartInterviewProcess.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByName(String name);

	@Query("select e from Employee e where e.wissenId = :wissen_id")
	public Employee findByWissenId(@Param("wissen_id") String wissenid);

	
	
	@Query("select e from Employee e where e.active = :active")
	public List<Employee> findByActive(@Param("active") boolean active,Pageable page);
	
	@Query("select e from Employee e where e.email = :email")
	public Employee findByEmail(@Param("email") String email);
}
