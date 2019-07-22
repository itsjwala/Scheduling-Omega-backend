package com.wissen.SmartInterviewProcess.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.AvailableSlot;

public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Long> {

	@Query("select e from AvailableSlot e where e.fromTimestamp >= :from and e.toTimestamp<= :to and active= :active")
	public List<AvailableSlot> getAllBetween(@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot, @Param("active") boolean active);

	public List<AvailableSlot> findByActiveTrue();

	@Query("select e from AvailableSlot e where e.fromTimestamp >= :from and e.toTimestamp<= :to and e.interviewer.id = :interviewer_id and e.scheduled = 0 and e.active= :active")
	public List<AvailableSlot> getAllBetweenByInterviewer(@Param("from") LocalDateTime fromt,
			@Param("to") LocalDateTime tot, @Param("interviewer_id") long interviewerId,@Param("active") boolean active);

}
