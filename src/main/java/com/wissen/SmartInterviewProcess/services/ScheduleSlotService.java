package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.dto.SlotDTO;
import com.wissen.SmartInterviewProcess.models.AvailableSlot;
import com.wissen.SmartInterviewProcess.models.Candidate;
import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.models.Technology;
import com.wissen.SmartInterviewProcess.repository.AvailableSlotRepository;
import com.wissen.SmartInterviewProcess.repository.CandidateRepository;
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

	@Autowired
	private CandidateRepository candidateRepository;

	@Transactional
	public ScheduleResponseDTO scheduleInterview(ScheduleRequestDTO scheduleSlotDTO) throws NotFoundException {

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

		Technology technology = technologyRepository.findById(scheduleSlotDTO.getTechnologyId()).orElseThrow(() -> {

			return new NotFoundException("Technology not found with id :" + scheduleSlotDTO.getTechnologyId());
		});

		scheduleSlot.setTechnology(technology);

		AvailableSlot availableSlot = availableSlotRepository.findById(scheduleSlotDTO.getSlotId()).orElseThrow(() -> {

			return new NotFoundException("Slot not found with id :" + scheduleSlotDTO.getSlotId());
		});
		availableSlot.setScheduled(true);
		availableSlotRepository.save(availableSlot);
		scheduleSlot.setSlot(availableSlot);

		scheduleSlot.setInterviewDescription(scheduleSlotDTO.getInterviewDescription());
		Candidate candidate = candidateRepository.save(scheduleSlotDTO.getCandidate());
		scheduleSlot.setCandidate(candidate);

		ScheduleSlot scheduled = scheduleSlotRepository.save(scheduleSlot);
		ScheduleResponseDTO response = new ScheduleResponseDTO();

		response.setCandidate(scheduled.getCandidate());
		response.setInterviewDescription(scheduled.getInterviewDescription());
		response.setInterviewerName(scheduled.getInterviewer().getEmp().getName());
		response.setInterviewerId(scheduled.getInterviewer().getId());
		response.setLevel(scheduled.getLevel().getLevel());
		response.setTechnology(scheduled.getTechnology().getTechnology());
		response.setScheduleID(scheduled.getId());
		response.setSlot(new SlotDTO(scheduled.getSlot().getFromTimestamp(), scheduled.getSlot().getToTimestamp()));
		return response;
	}

	@Transactional(readOnly = true)
	public List<ScheduleResponseDTO> getScheduleBetweenForHr(Long id, LocalDateTime from, LocalDateTime to)
			throws NotFoundException {
		employeeRepository.findById(id).orElseThrow(() -> {

			return new NotFoundException("Hr not found with id :" + id);
		});

		return scheduleSlotRepository.getAllBetweenByHr(id, from, to, false, true).stream().map(scheduled -> {
			ScheduleResponseDTO response = new ScheduleResponseDTO();

			response.setCandidate(scheduled.getCandidate());
			response.setInterviewDescription(scheduled.getInterviewDescription());
			response.setInterviewerName(scheduled.getInterviewer().getEmp().getName());
			response.setInterviewerId(scheduled.getInterviewer().getId());
			response.setLevel(scheduled.getLevel().getLevel());
			response.setTechnology(scheduled.getTechnology().getTechnology());
			response.setScheduleID(scheduled.getId());
			response.setSlot(new SlotDTO(scheduled.getSlot().getFromTimestamp(), scheduled.getSlot().getToTimestamp()));

			return response;
		}).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ScheduleResponseDTO> getScheduleBetweenForInterviewer(Long id, LocalDateTime from, LocalDateTime to)
			throws NotFoundException {
		interviewerRepository.findById(id).orElseThrow(() -> {

			return new NotFoundException("Interviewer not found with id :" + id);
		});

		return scheduleSlotRepository.getAllBetweenByInterviewer(id, from, to, false, true).stream().map(scheduled -> {
			ScheduleResponseDTO response = new ScheduleResponseDTO();

			response.setCandidate(scheduled.getCandidate());
			response.setInterviewDescription(scheduled.getInterviewDescription());
			response.setInterviewerName(scheduled.getInterviewer().getEmp().getName());
			response.setInterviewerId(scheduled.getInterviewer().getId());
			response.setLevel(scheduled.getLevel().getLevel());
			response.setTechnology(scheduled.getTechnology().getTechnology());
			response.setScheduleID(scheduled.getId());
			response.setSlot(new SlotDTO(scheduled.getSlot().getFromTimestamp(), scheduled.getSlot().getToTimestamp()));

			return response;

		}).collect(Collectors.toList());
	}

	@Transactional
	public void cancelScheduleInterviewByInterviewer(long scheduleId, String cancellationReason) throws NotFoundException {

		ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(scheduleId).orElseThrow(() -> {

			return new NotFoundException("schedule slot not found with id :" + scheduleId);
		});

		scheduleSlot.getSlot().setActive(false);

		scheduleSlot.setCancelled(true);

		scheduleSlot.setCancellationReason(cancellationReason);
		scheduleSlot.setScheduleCanceller(scheduleSlot.getInterviewer().getEmp());
		scheduleSlotRepository.save(scheduleSlot);

	}
	
	
	@Transactional
	public void cancelScheduleInterviewByHr(long scheduleId, String cancellationReason) throws NotFoundException {

		ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(scheduleId).orElseThrow(() -> {

			return new NotFoundException("schedule slot not found with id :" + scheduleId);
		});

		scheduleSlot.getSlot().setScheduled(false);
		
		scheduleSlot.setCancelled(true);

		scheduleSlot.setCancellationReason(cancellationReason);
		scheduleSlot.setScheduleCanceller(scheduleSlot.getHr());

		scheduleSlotRepository.save(scheduleSlot);

	}
	
	

}
