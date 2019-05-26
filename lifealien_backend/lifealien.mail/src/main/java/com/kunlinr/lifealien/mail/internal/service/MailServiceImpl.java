package com.kunlinr.lifealien.mail.internal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kunlinr.lifealien.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	
	public boolean mail(String to,String subject,String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			mailSender.send(msg);
			return true;
		}catch(Exception e) {
			throw new RuntimeException("发送失败");
		}
	}
}
