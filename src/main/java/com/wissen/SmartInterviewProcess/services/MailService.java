package com.wissen.SmartInterviewProcess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	public JavaMailSender javaMailSender;
	
	public void sendMail() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("jigar.wala@wissen.com", "prithviraj.maurya@wissen.com");
		mailMessage.setCc("anirudh.balakka@wissen.com");
		mailMessage.setSubject("TEST");
		mailMessage.setText("This is a test email");
		javaMailSender.send(mailMessage);
	}
}
