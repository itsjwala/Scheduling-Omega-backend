package com.wissen.SmartInterviewProcess.dto;

import com.wissen.SmartInterviewProcess.models.AvailableSlot;

public class AvailableSlotDTO {
	private long slotId;
	private SlotDTO slot;
	
	public AvailableSlotDTO() {
		
	}
	
	public AvailableSlotDTO(AvailableSlot availableSlot ) {
		this.slotId = availableSlot.getId();
		this.slot = new SlotDTO(availableSlot.getFromTimestamp(), availableSlot.getToTimestamp());
	}

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
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
	private boolean scheduled;

	@Override
	public String toString() {
		return "AvailableSlotDTO [slotId=" + slotId + ", slot=" + slot + ", scheduled=" + scheduled + "]";
	}
}
