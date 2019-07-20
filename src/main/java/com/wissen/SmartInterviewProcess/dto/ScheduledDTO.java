package com.wissen.SmartInterviewProcess.dto;

public class ScheduledDTO {

	private long id;
	private ScheduleSlotDTO scheduleSlotDTO;

	public ScheduledDTO() {
		
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ScheduleSlotDTO getScheduleSlotDTO() {
		return scheduleSlotDTO;
	}

	public void setScheduleSlotDTO(ScheduleSlotDTO scheduleSlotDTO) {
		this.scheduleSlotDTO = scheduleSlotDTO;
	}

}
