package com.tmax.cm.superstore.user.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender javaMailSender;
	public EmailAuthResponseDto authEmail(EmailAuthRequestDto emailAuthRequestDto) throws IllegalAccessException {
		String validate_num = Integer.toString(ThreadLocalRandom.current().nextInt(100000, 1000000));
		try {
			MimeMessage message = createMessage(validate_num, emailAuthRequestDto.getEmail());
			javaMailSender.send(message);
		} catch (MessagingException e) {
			throw new IllegalAccessException();
		}
		return EmailAuthResponseDto.builder().valid_num(validate_num).build();
	}

	public MimeMessage createMessage(String code, String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.addRecipients(Message.RecipientType.TO, email);
		message.setSubject("[슈퍼스토어] 이메일 인증 번호입니다.");
		message.setText("이메일 인증코드 : " + code);
		message.setFrom("totw2018@naver.com");
		return message;
	}
}
