package com.wissen.SmartInterviewProcess.dto;

public class AvailableSlotDTO {
	private long slotId;
	private SlotDTO slot;

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
