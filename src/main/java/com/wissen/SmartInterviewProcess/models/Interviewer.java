package com.wissen.SmartInterviewProcess.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="interviewer")
public class Interviewer {

	@Id
//	@Column(name = "interviewer_id")
	private long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Employee emp;

	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Level> levels;

	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Technology> technologies;
	// @OneToMany(fetch=FetchType.LAZY)
	// private List<AvailableSlot> availableSlots;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
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

	@Override
	public String toString() {
		return "Interviewer [id=" + id + ", emp=" + emp + ", levels=" + levels + ", technologies=" + technologies + "]";
	}

}
