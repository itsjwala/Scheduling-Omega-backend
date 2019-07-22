package com.wissen.SmartInterviewProcess.dto;

import com.wissen.SmartInterviewProcess.models.Candidate;

public class ScheduleResponseDTO {

	private long scheduleID;
	private SlotDTO slot;
	private long interviewerId;
	
	private String interviewerName;
	private String level;
	private String technology;

	private Candidate candidate;

	private String interviewDescription;

	public long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public SlotDTO getSlot() {
		return slot;
	}

	public void setSlot(SlotDTO slot) {
		this.slot = slot;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getInterviewDescription() {
		return interviewDescription;
	}

	public void setInterviewDescription(String interviewDescription) {
		this.interviewDescription = interviewDescription;
	}

	@Override
	public String toString() {
		return "ScheduleResponseDTO [scheduleID=" + scheduleID + ", slot=" + slot + ", interviewerName="
				+ interviewerName + ", level=" + level + ", technology=" + technology + ", candidate=" + candidate
				+ ", interviewDescription=" + interviewDescription + "]";
	}

}
