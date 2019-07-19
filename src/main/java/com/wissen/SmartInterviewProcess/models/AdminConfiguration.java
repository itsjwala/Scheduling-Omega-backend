package com.wissen.SmartInterviewProcess.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminConfiguration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	private int interviewDurationMinutes;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getInterviewDurationMinutes() {
		return interviewDurationMinutes;
	}


	public void setInterviewDurationMinutes(int interviewDurationMinutes) {
		this.interviewDurationMinutes = interviewDurationMinutes;
	}


	@Override
	public String toString() {
		return "AdminConfiguration [id=" + id + ", interviewDurationMinutes=" + interviewDurationMinutes + "]";
	}
	
	
	
	
}
