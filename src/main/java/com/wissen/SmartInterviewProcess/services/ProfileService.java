package com.wissen.SmartInterviewProcess.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

@Service
public class ProfileService {
	
	@Autowired
	TechnologyRepository technologyRepository;
	
	@Autowired 
	LevelRepository levelRepository;
	
	@Autowired
	InterviewerRepository interviewerRepository;
	
	@Transactional
	public Interviewer editPreferences(Long id, InterviewerDTO interviewerDTO) {
		Interviewer interviewer = interviewerRepository.findById(id).get();
		
		interviewer.setLevels(interviewerDTO.getLevels());
		interviewer.setTechnologies(interviewerDTO.getTechnologies());
		
		return interviewerRepository.save(interviewer);
	}

}
