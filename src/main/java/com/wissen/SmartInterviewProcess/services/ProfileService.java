package com.wissen.SmartInterviewProcess.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

@Service
public class ProfileService {
	
	@Autowired
	TechnologyRepository technologyRepository;
	
	@Autowired 
	LevelRepository levelRepository;
	
	@Transactional
	public List<Level> allLevels() {
		return levelRepository.findAll();
	}
	
	@Transactional
	public List<Technology> allTechnologies() {
		return technologyRepository.findAll();
	}

}
