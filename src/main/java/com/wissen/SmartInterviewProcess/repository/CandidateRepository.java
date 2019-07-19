package com.wissen.SmartInterviewProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.SmartInterviewProcess.models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
