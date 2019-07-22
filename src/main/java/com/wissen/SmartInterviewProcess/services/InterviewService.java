package com.wissen.SmartInterviewProcess.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.SmartInterviewProcess.dto.InterviewerDTO;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;

@Service
public class InterviewService {

	@Autowired
	InterviewerRepository interviewerRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	public Interviewer addInterviewer(InterviewerDTO interviewerDTO) {
		Employee newEmployee = employeeService.addEmployee(interviewerDTO.getEmp());
		
		Interviewer interviewer = new Interviewer();
		System.out.println(newEmployee);
//		interviewer.setId(newEmployee.getId());
		interviewer.setEmp(newEmployee);
		interviewer.setLevels(interviewerDTO.getLevels());
		interviewer.setTechnologies(interviewerDTO.getTechnologies());
		System.out.println(interviewer);
		return interviewerRepository.save(interviewer);	
	}
	
	public List<Interviewer> allInterviewers() {
		return interviewerRepository.findAll();
	}
}
