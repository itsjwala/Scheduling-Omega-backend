package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleFeedbackDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.repository.EmployeeRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.ScheduleSlotRepository;

import javassist.NotFoundException;

@Service
public class FeedbackService {
	
	@Autowired
	private ScheduleSlotRepository scheduleSlotRepository;
	
	@Autowired
	private InterviewerRepository interviewerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ScheduleSlotService scheduleSlotService;
	
	@Transactional
	public Long addFeedback(FeedbackDTO feedbackDTO) throws NotFoundException{
		
		ScheduleSlot scheduledSlot = scheduleSlotRepository.findById(feedbackDTO.getScheduleId()).orElseThrow(() -> {
			return new NotFoundException("Scheduled interview not found with id :" + feedbackDTO.getScheduleId());
		});
		
		scheduledSlot.setFeedback(feedbackDTO.getFeedback());
		scheduledSlot.setStatus(feedbackDTO.getStatus());
		
		scheduleSlotRepository.save(scheduledSlot);
		
		return feedbackDTO.getScheduleId();	
	}
	
	public List<FeedbackDTO> viewFeedback(Long interviewerId, LocalDateTime from, LocalDateTime to) throws NotFoundException {
		interviewerRepository.findById(interviewerId).orElseThrow(() -> {
			return new NotFoundException("Interviewer not found with id :" + interviewerId);
		});
		
		return scheduleSlotRepository.getAllBetweenByInterviewer(interviewerId, from, to, false, true).stream()
		.map(scheduledInterview -> {
			FeedbackDTO feedbackDTO = new FeedbackDTO();
			
			feedbackDTO.setScheduleId(scheduledInterview.getId());
			feedbackDTO.setFeedback(scheduledInterview.getFeedback());
			feedbackDTO.setStatus(scheduledInterview.getStatus());
			
			feedbackDTO.setScheduleRequestDTO(new ScheduleRequestDTO());
			
			feedbackDTO.getScheduleRequestDTO().setHrId(scheduledInterview.getHr().getId());
			feedbackDTO.getScheduleRequestDTO().setInterviewerId(scheduledInterview.getInterviewer().getId());
			feedbackDTO.getScheduleRequestDTO().setSlotId(scheduledInterview.getSlot().getId());
			feedbackDTO.getScheduleRequestDTO().setCandidate(scheduledInterview.getCandidate());
			feedbackDTO.getScheduleRequestDTO().setLevelId(scheduledInterview.getLevel().getId());
			feedbackDTO.getScheduleRequestDTO().setTechnology(scheduledInterview.getTechnology().getId());
			feedbackDTO.getScheduleRequestDTO().setInterviewDescription(scheduledInterview.getInterviewDescription());
			
			return feedbackDTO;
		}).collect(Collectors.toList());

	}

	public List<ScheduleFeedbackDTO> viewFeedbackInterviewer(Long interviewerId) throws NotFoundException {
		interviewerRepository.findById(interviewerId).orElseThrow(() -> {
			return new NotFoundException("Interviewer not found with id :" + interviewerId);
		});
		
		List<ScheduleResponseDTO> schedule = scheduleSlotService.allInterviewersSchedule(interviewerId);
		
		return schedule.stream().map(curr -> {
			ScheduleFeedbackDTO dto = new ScheduleFeedbackDTO();
			dto.setScheduleResponseDTO(curr);
			ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(dto.getScheduleResponseDTO().getScheduleID()).get();
			FeedbackDTO feedbackDTO = new FeedbackDTO();
			feedbackDTO.setFeedback(scheduleSlot.getFeedback());
			feedbackDTO.setStatus(scheduleSlot.getStatus());
			dto.setFeedbackDTO(feedbackDTO);
			return dto;
		}).collect(Collectors.toList());
	}
	
	public List<ScheduleFeedbackDTO> viewFeedbackHr(Long hrId) throws NotFoundException {
		employeeRepository.findById(hrId).orElseThrow(() -> {
			return new NotFoundException("HR not found with id :" + hrId);
		});
		
		List<ScheduleResponseDTO> schedule = scheduleSlotService.allHrsSchedule(hrId);
		
		return schedule.stream().map(curr -> {
			ScheduleFeedbackDTO dto = new ScheduleFeedbackDTO();
			dto.setScheduleResponseDTO(curr);
			ScheduleSlot scheduleSlot = scheduleSlotRepository.findById(dto.getScheduleResponseDTO().getScheduleID()).get();
			FeedbackDTO feedbackDTO = new FeedbackDTO();
			feedbackDTO.setFeedback(scheduleSlot.getFeedback());
			feedbackDTO.setStatus(scheduleSlot.getStatus());
			dto.setFeedbackDTO(feedbackDTO);
			return dto;
		}).collect(Collectors.toList());
	}

}
