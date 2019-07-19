package com.wissen.SmartInterviewProcess.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.ScheduleSlot;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
	
	
	@Query("select e from ScheduleSlot e where e.hr.id = :hr_id and e.active= :active and e.cancelled = :cancelled")
	public List<ScheduleSlot> getAllByHr(@Param("hr_id") long hrId,@Param("cancelled")boolean cancelled,@Param("active") boolean active);
	
	
	
	@Query("select e from ScheduleSlot e where e.interviewer.id= :interviewer_id and e.slot.fromTimestamp >= :from and e.slot.toTimestamp<= :to and e.active= :active and e.cancelled = :cancelled")
	public List<ScheduleSlot> getAllBetweenByInterviewer(@Param("interviewer_id")long interviewerId,@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot,@Param("cancelled")boolean cancelled,@Param("active") boolean active);

	@Query("select e from ScheduleSlot e where e.interviewer.id = :interviewer_id and e.active= :active and e.cancelled = :cancelled")
	public List<ScheduleSlot> getAllByInterviewer(@Param("interviewer_id") long interviewerId,@Param("cancelled")boolean cancelled,@Param("active") boolean active);


}
