package com.wissen.SmartInterviewProcess.dto;

import java.util.List;

import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.Technology;

public class AvailableSlotForScheduleDTO {

	private long slotId;
	
	private SlotDTO slot;
	
	private long interviewerId;
	
	private String interviewerName;
	
	private List<Level> levels;
	
	private List<Technology> technologies;
	
	private boolean scheduled;

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public SlotDTO getSlot() {
		return slot;
	}

	public void setSlot(SlotDTO slot) {
		this.slot = slot;
	}

	public long getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(long interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	@Override
	public String toString() {
		return "AvailableSlotForScheduleDTO [slotId=" + slotId + ", slot=" + slot + ", interviewerId=" + interviewerId
				+ ", interviewerName=" + interviewerName + ", levels=" + levels + ", technologies=" + technologies
				+ ", scheduled=" + scheduled + "]";
	}
	
}
