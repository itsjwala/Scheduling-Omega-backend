package com.wissen.SmartInterviewProcess.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.SmartInterviewProcess.models.Employee;
import com.wissen.SmartInterviewProcess.models.Interviewer;
import com.wissen.SmartInterviewProcess.models.Level;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;
import com.wissen.SmartInterviewProcess.models.Technology;

public interface ReportRepository extends JpaRepository<ScheduleSlot, Long>{
	
	@Query("select e.interviewer.emp.name as interviewerName, e.interviewer.emp.wissenId as interviewerId, e.level as interviewLevel,	e.technology as interviewTechnology, count(*) as count from ScheduleSlot e where e.slot.fromTimestamp >= :from and e.slot.toTimestamp<= :to and e.cancelled=false group by e.interviewer, e.level, e.technology")
	public List<InterviewerReportDTO> interviewerReport(@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot);
	
//	@Query(nativeQuery = true, value = "select interviewer_id as interviewerId, emp.name as interviewerName, count(*) as count from smartinterviewdb.schedule_slot slot join employee emp on (slot.interviewer_id = emp.id) where slot_id in (select slot_id from available_slot where from_timestamp between '2019-07-01' and '2019-07-30' and to_timestamp between '2019-07-01' and '2019-07-30') group by interviewer_id;")
//	@Query(nativeQuery = true, value = "select interviewer_id as interviewerId, emp.name as interviewerName, emp.interviewer.technology as tech count(*) as count from smartinterviewdb.schedule_slot slot join employee emp on (slot.interviewer_id = emp.id) where slot_id in (select slot_id from available_slot where from_timestamp between :from and :to and to_timestamp between :from and :to) and cancelled = false group by interviewer_id;")
//	public List<ReportDTO> interviewerReport(@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot);
	
	@Query("select e.hr.name as hrName, e.hr.email as hrEmail, e.hr.active as hrActive, e.hr.wissenId as hrWissenId, e.hr.phoneNumber as hrPhone , count(*) as count from ScheduleSlot e where e.slot.fromTimestamp >= :from and e.slot.toTimestamp<= :to and e.cancelled=false group by e.hr")
	public List<HrReportDTO> hrReport(@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot);
	
//	select i from Interviewer i where i.id not in 
//	@Query("select i from Interviewer i where i.id not in (select distinct e.interviewer.id from AvailableSlot e where e.fromTimestamp >= :from and e.toTimestamp<= :to)")
	@Query("select i.emp.name as interviewerName, i.emp.phoneNumber as interviewerPhone, i.emp.email as interviewerEmail, i.emp.wissenId as interviewerId from interviewer i where i.id not in (select distinct e.interviewer.id from AvailableSlot e where e.fromTimestamp >= :from and e.toTimestamp<= :to)")
	public List<NoSlotDTO> noSlotsGiven(@Param("from") LocalDateTime fromt, @Param("to") LocalDateTime tot);
	
	public static interface InterviewerReportDTO {
//		Interviewer getInterviewerDetails();
		String getInterviewerId();
		String getInterviewerName();
//		Employee getInterviewerDetails();
		Long getCount();
		Level getInterviewLevel();
		Technology getInterviewTechnology();
	}
	
	public static interface HrReportDTO {
//		Employee getHrDetails();
		String getHrName();
		String getHrEmail();
		String getHrWissenId();
		String getHrPhone();
		Boolean getHrActive();
		Long getCount();
	}
	
	public static interface NoSlotDTO {
		String getInterviewerName();
		String getInterviewerId();
		String getInterviewerPhone();
		String getInterviewerEmail();

	}
}
