package com.wissen.SmartInterviewProcess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.ScheduleSlotDTO;
import com.wissen.SmartInterviewProcess.models.AvailableSlot;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.AvailableSlotRepository;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.LevelRepository;
import com.wissen.SmartInterviewProcess.repository.ScheduleSlotRepository;
import com.wissen.SmartInterviewProcess.repository.TechnologyRepository;

import javassist.NotFoundException;

@Service
public class ScheduleSlotService {

	@Autowired
	private ScheduleSlotRepository scheduleSlotRepository;

	@Autowired
	private AvailableSlotRepository availableSlotRepository;

	@Autowired
	private TechnologyRepository technologyRepository;

	@Autowired
	private LevelRepository levelRepository;

	@Autowired
	private InterviewerRepository interviewerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public long ScheduleInterview(ScheduleSlotDTO scheduleSlotDTO) throws NotFoundException {

		ScheduleSlot scheduleSlot = new ScheduleSlot();

		Employee hr = employeeRepository.findById(scheduleSlotDTO.getHrId()).orElseThrow(() -> {

			return new NotFoundException("Hr not found with id :" + scheduleSlotDTO.getHrId());
		});
		scheduleSlot.setHr(hr);

		Interviewer interviewer = interviewerRepository.findById(scheduleSlotDTO.getInterviewerId()).orElseThrow(() -> {

			return new NotFoundException("Interviewer not found with id :" + scheduleSlotDTO.getInterviewerId());
		});

		scheduleSlot.setInterviewer(interviewer);

		Level level = levelRepository.findById(scheduleSlotDTO.getLevelId()).orElseThrow(() -> {

			return new NotFoundException("Level not found with id :" + scheduleSlotDTO.getLevelId());
		});

		scheduleSlot.setLevel(level);

		Technology technology = technologyRepository.findById(scheduleSlotDTO.getTechnology()).orElseThrow(() -> {

			return new NotFoundException("Technology not found with id :" + scheduleSlotDTO.getTechnology());
		});

		scheduleSlot.settechnology(technology);

		AvailableSlot availableSlot = availableSlotRepository.findById(scheduleSlotDTO.getSlotId()).orElseThrow(() -> {

			return new NotFoundException("Slot not found with id :" + scheduleSlotDTO.getSlotId());
		});
		
		scheduleSlot.setSlot(availableSlot);

		
		scheduleSlot.setInterviewDescription(scheduleSlotDTO.getInterviewDescription());
		scheduleSlot.setCandidate(scheduleSlotDTO.getCandidate());
		
		return scheduleSlotRepository.save(scheduleSlot).getSlot().getId();
	}

}
