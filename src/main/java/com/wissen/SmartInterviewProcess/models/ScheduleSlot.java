package com.wissen.SmartInterviewProcess.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ScheduleSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "slot_id")
	private AvailableSlot slot;

	@ManyToOne
	@JoinColumn(name = "hr_id")
	private Employee hr;

	@ManyToOne
	@JoinColumn(name = "interviewer_id")
	private Interviewer interviewer;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee scheduleCanceller;

	private String interviewDescription;

	private boolean cancelled = false;

	private String cancellationReason;

	private String feedback;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	private boolean active = true;

	private Status status = Status.PENDING;

	@ManyToOne
	private Level level;

	@ManyToOne
	private Technology technology;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AvailableSlot getSlot() {
		return slot;
	}

	public void setSlot(AvailableSlot slot) {
		this.slot = slot;
	}

	public Employee getHr() {
		return hr;
	}

	public void setHr(Employee hr) {
		this.hr = hr;
	}

	public Interviewer getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(Interviewer interviewer) {
		this.interviewer = interviewer;
	}

	public Employee getScheduleCanceller() {
		return scheduleCanceller;
	}

	public void setScheduleCanceller(Employee scheduleCanceller) {
		this.scheduleCanceller = scheduleCanceller;
	}

	public String getInterviewDescription() {
		return interviewDescription;
	}

	public void setInterviewDescription(String interviewDescription) {
		this.interviewDescription = interviewDescription;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	@Override
	public String toString() {
		return "ScheduleSlot [id=" + id + ", slot=" + slot + ", hr=" + hr + ", interviewer=" + interviewer
				+ ", scheduleCanceller=" + scheduleCanceller + ", interviewDescription=" + interviewDescription
				+ ", cancelled=" + cancelled + ", cancellationReason=" + cancellationReason + ", feedback=" + feedback
				+ ", candidate=" + candidate + ", active=" + active + ", status=" + status + ", level=" + level
				+ ", technology=" + technology + "]";
	}

}
