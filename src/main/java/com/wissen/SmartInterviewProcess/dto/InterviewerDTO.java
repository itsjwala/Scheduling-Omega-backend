package com.wissen.SmartInterviewProcess.dto;

import java.util.List;

import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;

public class InterviewerDTO {
	
	private EmployeeDTO emp;

	private List<Level> levels;

	private List<Technology> technologies;

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	

	public EmployeeDTO getEmp() {
		return emp;
	}



	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
	
	


}
