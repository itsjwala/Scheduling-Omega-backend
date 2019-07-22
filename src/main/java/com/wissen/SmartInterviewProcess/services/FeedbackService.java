package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;
import com.wissen.SmartInterviewProcess.repository.ScheduleSlotRepository;

import javassist.NotFoundException;

@Service
public class FeedbackService {
	
	@Autowired
	private ScheduleSlotRepository scheduleSlotRepository;
	
	@Autowired
	private InterviewerRepository interviewerRepository;
	
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
			feedbackDTO.getScheduleRequestDTO().setTechnology(scheduledInterview.gettechnology().getId());
			feedbackDTO.getScheduleRequestDTO().setInterviewDescription(scheduledInterview.getInterviewDescription());
			
			return feedbackDTO;
		}).collect(Collectors.toList());

	}

}
