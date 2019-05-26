package com.kunlinr.lifealien.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunlinr.lifealien.mail.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	
	@Autowired
	MailService mailService;
	
	/**
	 * @param to 发送给谁：XXX@XXX.XX
	 * @param subject 邮件主题
	 * @param text 邮件内容
	 */
	@RequestMapping("simpleMail")
	public boolean SimpleMail(@Param(value = "to") String to,
			@Param(value = "subject") String subject,
			@Param(value = "text") String text) {
		return mailService.mail(to, subject, text);
	}
}
