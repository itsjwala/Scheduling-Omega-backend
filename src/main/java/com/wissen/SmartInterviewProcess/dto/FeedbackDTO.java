package com.wissen.SmartInterviewProcess.dto;

import com.wissen.SmartInterviewProcess.models.Status;

public class FeedbackDTO {

	private long scheduleId;
	private String feedback;
	private Status status;
	private ScheduleRequestDTO ScheduleRequestDTO;
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
	public ScheduleRequestDTO getScheduleRequestDTO() {
		return ScheduleRequestDTO;
	}
	public void setScheduleRequestDTO(ScheduleRequestDTO scheduleRequestDTO) {
		ScheduleRequestDTO = scheduleRequestDTO;
	}
	@Override
	public String toString() {
		return "FeedbackDTO [scheduleId=" + scheduleId + ", feedback=" + feedback + ", status=" + status
				+ ", ScheduleRequestDTO=" + ScheduleRequestDTO + "]";
	}


	
}
