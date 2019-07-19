package com.wissen.SmartInterviewProcess.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AvailableSlot {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private LocalDateTime fromTimestamp;
	
	private LocalDateTime toTimestamp;
	
	@ManyToOne()
	@JoinColumn(name="interviewer_id")
	private Interviewer interviewer;
	
	private boolean scheduled=false;

	private boolean active=true;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFromTimestamp() {
		return fromTimestamp;
	}

	public void setFromTimestamp(LocalDateTime fromTimestamp) {
		this.fromTimestamp = fromTimestamp;
	}

	public LocalDateTime getToTimestamp() {
		return toTimestamp;
	}

	public void setToTimestamp(LocalDateTime toTimestamp) {
		this.toTimestamp = toTimestamp;
	}

	public Interviewer getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(Interviewer interviewer) {
		this.interviewer = interviewer;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "AvailableSlot [id=" + id + ", fromTimestamp=" + fromTimestamp + ", toTimestamp=" + toTimestamp
				+ ", interviewer=" + interviewer + ", scheduled=" + scheduled + ", active=" + active + "]";
	}
	
	
	
	
}
