package com.wissen.SmartInterviewProcess.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wissen.SmartInterviewProcess.dto.AvailableSlotDTO;
import com.wissen.SmartInterviewProcess.dto.AvailableSlotForScheduleDTO;
import com.wissen.SmartInterviewProcess.dto.SlotDTO;
import com.wissen.SmartInterviewProcess.models.AvailableSlot;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.repository.AvailableSlotRepository;
import com.wissen.SmartInterviewProcess.repository.InterviewerRepository;

import javassist.NotFoundException;

@Service
public class AvailableSlotService {

	@Autowired
	private AvailableSlotRepository availableSlotRepository;
	@Autowired
	private InterviewerRepository interviewerRepository;

	@Transactional
	public void addSlots(long interviewerId, List<SlotDTO> slots) throws NotFoundException {

		Interviewer interviewer = interviewerRepository.findById(interviewerId).orElseThrow(() -> {

			return new NotFoundException("Interviewer not found with id :" + interviewerId);
		});

		for (SlotDTO slot : slots) {

			AvailableSlot availableSlot = new AvailableSlot();

			availableSlot.setFromTimestamp(slot.getFrom());
			availableSlot.setToTimestamp(slot.getTo());
			availableSlot.setInterviewer(interviewer);

			availableSlotRepository.save(availableSlot);

		}

	}

	@Transactional(readOnly = true)
	public List<AvailableSlotForScheduleDTO> getSlotsBetween(LocalDateTime from, LocalDateTime to) {

		return availableSlotRepository.getAllBetween(from, to, true).stream().map(availableSlot -> {
			AvailableSlotForScheduleDTO slotForSchedule = new AvailableSlotForScheduleDTO();

			slotForSchedule.setInterviewerId(availableSlot.getInterviewer().getId());

			slotForSchedule.setInterviewerName(availableSlot.getInterviewer().getEmp().getName());

			slotForSchedule.setLevels(availableSlot.getInterviewer().getLevels());

			slotForSchedule.setScheduled(availableSlot.isScheduled());
			slotForSchedule.setSlot(new SlotDTO(availableSlot.getFromTimestamp(), availableSlot.getToTimestamp()));
			slotForSchedule.setSlotId(availableSlot.getId());
			slotForSchedule.setTechnologies(availableSlot.getInterviewer().getTechnologies());

			return slotForSchedule;
		}).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public List<AvailableSlotDTO> getSlotsBetween(long interviewerId, LocalDateTime fromt, LocalDateTime tot)
			throws NotFoundException {
		interviewerRepository.findById(interviewerId).orElseThrow(() -> {

			return new NotFoundException("Interviewer not found with id :" + interviewerId);
		});

		return availableSlotRepository.getAllBetweenByInterviewer(fromt, tot, interviewerId, true).stream()
				.map(availableSlot -> {

					AvailableSlotDTO availableSlotDTO = new AvailableSlotDTO();

					availableSlotDTO.setScheduled(availableSlot.isScheduled());
					availableSlotDTO.setSlotId(availableSlot.getId());
					availableSlotDTO
							.setSlot(new SlotDTO(availableSlot.getFromTimestamp(), availableSlot.getToTimestamp()));

					return availableSlotDTO;
				}).collect(Collectors.toList());

	}

}
