package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.ScheduleSlotDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduledDTO;
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
	public ScheduledDTO ScheduleInterview(ScheduleSlotDTO scheduleSlotDTO) throws NotFoundException {
		
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
		
		ScheduleSlot scheduled = scheduleSlotRepository.save(scheduleSlot);
		ScheduledDTO scheduledDTO = new ScheduledDTO();
		scheduledDTO.setId(scheduled.getId());
		scheduledDTO.setScheduleSlotDTO(scheduleSlotDTO);
		
		return scheduledDTO;
		

	}
	
	@Transactional(readOnly = true)
	public List<ScheduledDTO> getScheduleBetweenForHr(Long id, LocalDateTime from, LocalDateTime to) throws NotFoundException{
		employeeRepository.findById(id).orElseThrow(() -> {

			return new NotFoundException("Hr not found with id :" + id);
		});
		
		
		return scheduleSlotRepository.getAllBetweenByHr(id, from, to, false, true).stream()
				.map(scheduledInterview -> {
					ScheduledDTO scheduledDTO = new ScheduledDTO();
					scheduledDTO.setId(scheduledInterview.getId());
					scheduledDTO.setScheduleSlotDTO(new ScheduleSlotDTO());
					
					scheduledDTO.getScheduleSlotDTO().setHrId(scheduledInterview.getHr().getId());
					scheduledDTO.getScheduleSlotDTO().setInterviewerId(scheduledInterview.getInterviewer().getId());
					scheduledDTO.getScheduleSlotDTO().setSlotId(scheduledInterview.getSlot().getId());
					scheduledDTO.getScheduleSlotDTO().setCandidate(scheduledInterview.getCandidate());
					scheduledDTO.getScheduleSlotDTO().setLevelId(scheduledInterview.getLevel().getId());
					scheduledDTO.getScheduleSlotDTO().setTechnology(scheduledInterview.gettechnology().getId());
					scheduledDTO.getScheduleSlotDTO().setInterviewDescription(scheduledInterview.getInterviewDescription());
					
					return scheduledDTO;
				}).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ScheduledDTO> getScheduleBetweenForInterviewer(Long id, LocalDateTime from, LocalDateTime to) throws NotFoundException{
		interviewerRepository.findById(id).orElseThrow(() -> {

			return new NotFoundException("Interviewer not found with id :" + id);
		});
		
		
		return scheduleSlotRepository.getAllBetweenByInterviewer(id, from, to, false, true).stream()
				.map(scheduledInterview -> {
					ScheduledDTO scheduledDTO = new ScheduledDTO();
					scheduledDTO.setId(scheduledInterview.getId());
					scheduledDTO.setScheduleSlotDTO(new ScheduleSlotDTO());
					
					scheduledDTO.getScheduleSlotDTO().setHrId(scheduledInterview.getHr().getId());
					scheduledDTO.getScheduleSlotDTO().setInterviewerId(scheduledInterview.getInterviewer().getId());
					scheduledDTO.getScheduleSlotDTO().setSlotId(scheduledInterview.getSlot().getId());
					scheduledDTO.getScheduleSlotDTO().setCandidate(scheduledInterview.getCandidate());
					scheduledDTO.getScheduleSlotDTO().setLevelId(scheduledInterview.getLevel().getId());
					scheduledDTO.getScheduleSlotDTO().setTechnology(scheduledInterview.gettechnology().getId());
					scheduledDTO.getScheduleSlotDTO().setInterviewDescription(scheduledInterview.getInterviewDescription());
					
					return scheduledDTO;
				}).collect(Collectors.toList());
	}

}
