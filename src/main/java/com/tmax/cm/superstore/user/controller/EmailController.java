package com.tmax.cm.superstore.user.controller;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.user.dto.EmailAuthRequestDto;
import com.tmax.cm.superstore.user.dto.EmailAuthResponseDto;
import com.tmax.cm.superstore.user.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/email")
public class EmailController {
	private final EmailService emailService;
	@PostMapping
	public ResponseEntity<EmailAuthResponseDto> emailAuth(
		@RequestBody EmailAuthRequestDto emailAuthRequestDto) throws IllegalAccessException {
		return ResponseEntity.ok().body(emailService.authEmail(emailAuthRequestDto));
	}
}
