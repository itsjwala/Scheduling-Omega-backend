package com.wissen.SmartInterviewProcess.dto;

public class CancelDTO {

	private String cancellationReason;

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	@Override
	public String toString() {
		return "CancelDTO [cancellationReason=" + cancellationReason + "]";
	}
	
	
}
