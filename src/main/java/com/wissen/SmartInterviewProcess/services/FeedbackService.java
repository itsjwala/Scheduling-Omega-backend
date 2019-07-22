package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.FeedbackDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleSlotDTO;
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
			
			feedbackDTO.setScheduleSlotDTO(new ScheduleSlotDTO());
			
			feedbackDTO.getScheduleSlotDTO().setHrId(scheduledInterview.getHr().getId());
			feedbackDTO.getScheduleSlotDTO().setInterviewerId(scheduledInterview.getInterviewer().getId());
			feedbackDTO.getScheduleSlotDTO().setSlotId(scheduledInterview.getSlot().getId());
			feedbackDTO.getScheduleSlotDTO().setCandidate(scheduledInterview.getCandidate());
			feedbackDTO.getScheduleSlotDTO().setLevelId(scheduledInterview.getLevel().getId());
			feedbackDTO.getScheduleSlotDTO().setTechnology(scheduledInterview.gettechnology().getId());
			feedbackDTO.getScheduleSlotDTO().setInterviewDescription(scheduledInterview.getInterviewDescription());
			
			return feedbackDTO;
		}).collect(Collectors.toList());

	}

}
