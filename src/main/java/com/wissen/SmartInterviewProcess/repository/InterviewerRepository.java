package com.wissen.SmartInterviewProcess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.Interviewer;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	@Query("select new List(e.id,e.emp.name) from interviewer e")
	public List<List<Object>> getAllInterviewerName();

	@Query("select e from interviewer e where e.emp.wissenId = :wissen_id")
	public Interviewer findByWissenId(@Param("wissen_id") String wissenid);

}
