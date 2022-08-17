package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.GetAlICustomerCenterInquiryResponseDto;
import com.tmax.cm.superstore.mypage.service.CustomerCenterInquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/inquiry")
@RequiredArgsConstructor
public class CustomerCenterInquiryController {
	private final CustomerCenterInquiryService customerCenterInquiryService;

	@Transactional(readOnly = true)
	@GetMapping("/{userId}")
	public ResponseEntity<GetAlICustomerCenterInquiryResponseDto> getAllInquiry(@PathVariable UUID userId){
		return ResponseEntity.ok().body(customerCenterInquiryService.getAllInquiry(userId));
	}
}
