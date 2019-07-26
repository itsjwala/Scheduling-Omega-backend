package com.wissen.SmartInterviewProcess.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.wissen.SmartInterviewProcess.dto.EmployeeDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleRequestDTO;
import com.wissen.SmartInterviewProcess.dto.ScheduleResponseDTO;
import com.wissen.SmartInterviewProcess.models.ScheduleSlot;

@Component
public class MailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	ScheduleSlotService scheduleSlotService;
	
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setCc("prithviraj.maurya@wissen.com");
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		javaMailSender.send(mailMessage);
	}
	
	
	public void sendRegisteringMail(EmployeeDTO employeeDTO) {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String subject = "New " + employeeDTO.getRole() + " has registered";
		String to = employeeDTO.getEmail();
		String mailBody = "<html>"
				+ "			<body>"
				+ "					<h1>Smart Interview Processing</h1>"
				+ "					<hr>"
				+ " 				<h3>New " + employeeDTO.getRole() + " has registered</h3>"
				+ "					<p>Name: " + employeeDTO.getName() + "<br>Phone: " + employeeDTO.getPhoneNumber()
				+ "					<br>Wissen ID: " + employeeDTO.getWissenId()
				+ "			</body>"
				+ "			</html>";
		
		try {
			helper = new MimeMessageHelper(mailMessage, true);
			helper.setTo(to);
			helper.setCc("prithviraj.maurya@wissen.com");
			helper.setSubject(subject);
			helper.setText(mailBody, true);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		javaMailSender.send(mailMessage);
	}
	
	public void sendSchedulingMail(ScheduleRequestDTO scheduleRequestDTO, ScheduleResponseDTO scheduleResponseDTO) {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String to[] = scheduleSlotService.mailTo(scheduleRequestDTO.getInterviewerId(), scheduleRequestDTO.getHrId());
		String subject = "New Interview Scheduled";
		String mailBody = "<html>"
				+ "			<body>"
				+ "					<h1>Smart Interview Processing</h1>"
				+ "					<hr>"
				+ " 				<h3>New Interview Scheduled </h3>"
				+ "					<p>Time: " + scheduleResponseDTO.getSlot().getFrom()
				+ "					<br>Candidate Name: " + scheduleResponseDTO.getCandidate().getName()
				+ "					<br>Candidate Phone: " + scheduleResponseDTO.getCandidate().getPhoneNum()
				+ "					<br>Technology: " + scheduleResponseDTO.getLevel() 
				+ "					<br>Round: " + scheduleResponseDTO.getLevel()
				+ "			</body>"
				+ "			</html>";
		try {
			helper = new MimeMessageHelper(mailMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(mailBody, true);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		javaMailSender.send(mailMessage);
	}
	
	public void sendCancellationMail(ScheduleSlot scheduleSlot) {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		String to[] = scheduleSlotService.mailTo(scheduleSlot.getInterviewer().getId(), scheduleSlot.getHr().getId());
		String subject = "Interview Cancelled";
		String mailBody = "<html>"
				+ "			<body>"
				+ "					<h1>Smart Interview Processing</h1>"
				+ "					<hr>"
				+ " 				<h3>Interview Cancelled</h3>"
				+ "					<p>Time: " + scheduleSlot.getSlot().getFromTimestamp()
				+"					<br>Cancellation Reason: " + scheduleSlot.getCancellationReason()
				+ "					<br>Candidate Name: " + scheduleSlot.getCandidate().getName()
				+ "					<br>Candidate Phone: " + scheduleSlot.getCandidate().getPhoneNum()
				+ "					<br>Technology: " + scheduleSlot.getLevel() 
				+ "					<br>Round: " + scheduleSlot.getLevel()
				+ "			</body>"
				+ "			</html>";
		try {
			helper = new MimeMessageHelper(mailMessage, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(mailBody, true);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		javaMailSender.send(mailMessage);
	}
}
