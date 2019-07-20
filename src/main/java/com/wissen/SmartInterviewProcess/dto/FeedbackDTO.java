package com.wissen.SmartInterviewProcess.dto;

import com.wissen.SmartInterviewProcess.models.Status;

public class FeedbackDTO {

	private long scheduleId;
	private String feedback;
	private Status status;
	private ScheduleSlotDTO scheduleSlotDTO;
	
	public ScheduleSlotDTO getScheduleSlotDTO() {
		return scheduleSlotDTO;
	}

	public void setScheduleSlotDTO(ScheduleSlotDTO scheduleSlotDTO) {
		this.scheduleSlotDTO = scheduleSlotDTO;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FeedbackDTO [scheduleId=" + scheduleId + ", feedback=" + feedback + ", status=" + status
				+ ", scheduleSlotDTO=" + scheduleSlotDTO + "]";
	}

	
}
