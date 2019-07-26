package com.wissen.SmartInterviewProcess.dto;

public class ScheduleFeedbackDTO {
	
	private ScheduleResponseDTO scheduleResponseDTO;
	
	private FeedbackDTO feedbackDTO;

	public ScheduleResponseDTO getScheduleResponseDTO() {
		return scheduleResponseDTO;
	}

	public void setScheduleResponseDTO(ScheduleResponseDTO scheduleResponseDTO) {
		this.scheduleResponseDTO = scheduleResponseDTO;
	}

	public FeedbackDTO getFeedbackDTO() {
		return feedbackDTO;
	}

	public void setFeedbackDTO(FeedbackDTO feedbackDTO) {
		this.feedbackDTO = feedbackDTO;
	}

	@Override
	public String toString() {
		return "ScheduleFeedbackDTO [scheduleResponseDTO=" + scheduleResponseDTO + ", feedbackDTO=" + feedbackDTO + "]";
	}
	
	

}
