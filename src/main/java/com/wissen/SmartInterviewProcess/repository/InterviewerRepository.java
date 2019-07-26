package com.wissen.SmartInterviewProcess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.Interviewer;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	@Query("select e.id as interviewerId ,e.emp.name as interviewerName from interviewer e")
	public List<InterviewerNameDTO> getAllInterviewerName();

	@Query("select e from interviewer e where e.emp.wissenId = :wissen_id")
	public Interviewer findByWissenId(@Param("wissen_id") String wissenid);
	
	public static interface InterviewerNameDTO {
		Long getInterviewerId();
		String getInterviewerName();
	
	}

}
