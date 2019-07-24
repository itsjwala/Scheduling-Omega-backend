package com.wissen.SmartInterviewProcess.services;

import com.wissen.SmartInterviewProcess.models.Employee;

public interface UserService {
	void save(Employee employee);

	Employee findByEmail(String e);
}