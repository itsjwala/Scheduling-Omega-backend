package com.wissen.SmartInterviewProcess.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

@Service
public class InterviewService {

	@Autowired
	InterviewerRepository interviewerRepository;
	
	@Autowired
	LevelRepository levelRepository;
	
	@Autowired
	TechnologyRepository technologyRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Transactional
	public Interviewer addInterviewer(InterviewerDTO interviewerDTO) {
		Employee newEmployee = employeeService.addEmployee(interviewerDTO.getEmp());
		System.out.println(interviewerDTO);
		Interviewer interviewer = new Interviewer();
		System.out.println(newEmployee);
		interviewer.setEmp(newEmployee);
		
		List<Level> levels = interviewerDTO.getLevels().stream().map(level -> {
			return levelRepository.findById(level.getId()).get();
		}).collect(Collectors.toList());
		
		List<Technology> techs = interviewerDTO.getTechnologies().stream().map(tech -> {
			return technologyRepository.findById(tech.getId()).get();
		}).collect(Collectors.toList());
		
		interviewer.setLevels(levels);
		interviewer.setTechnologies(techs);
		System.out.println(interviewer);
		return interviewerRepository.save(interviewer);	
	}
	
	public List<Interviewer> allInterviewers() {
		return interviewerRepository.findAll();
	}
	
	
	public List<?> interviewerNames() {
		return interviewerRepository.getAllInterviewerName();
	}
}
