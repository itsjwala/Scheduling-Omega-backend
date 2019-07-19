package com.wissen.SmartInterviewProcess.dto;

import java.time.LocalDateTime;

public class SlotDTO {

	private LocalDateTime from;

	private LocalDateTime to;

	public SlotDTO() {

	}
	

	public SlotDTO(LocalDateTime from, LocalDateTime to) {
		this.from = from;
		this.to = to;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "SlotDTO [from=" + from + ", to=" + to + "]";
	}

}
