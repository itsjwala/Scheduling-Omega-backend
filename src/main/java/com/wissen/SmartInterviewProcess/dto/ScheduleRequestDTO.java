package com.wissen.SmartInterviewProcess.dto;

import com.wissen.SmartInterviewProcess.models.Candidate;

public class ScheduleRequestDTO {

	private long slotId;
	private long interviewerId;
	private long hrId;
	private String interviewDescription;
	private Candidate candidate;

	private long technology;

	private long levelId;

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public long getHrId() {
		return hrId;
	}

	public void setHrId(long hrId) {
		this.hrId = hrId;
	}

	public String getInterviewDescription() {
		return interviewDescription;
	}

	public void setInterviewDescription(String interviewDescription) {
		this.interviewDescription = interviewDescription;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public long getTechnology() {
		return technology;
	}

	public void setTechnology(long technology) {
		this.technology = technology;
	}

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	@Override
	public String toString() {
		return "ScheduleSlotDTO [slotId=" + slotId + ", interviewerId=" + interviewerId + ", hrId=" + hrId
				+ ", interviewDescription=" + interviewDescription + ", candidate=" + candidate + ", technology="
				+ technology + ", levelId=" + levelId + "]";
	}

}
