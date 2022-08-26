package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.CreateCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAlICustomerCenterInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetCustomerInquiryResponseDto;
import com.tmax.cm.superstore.mypage.service.CustomerInquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/inquiry")
@RequiredArgsConstructor
public class CustomerInquiryController {
	private final CustomerInquiryService customerInquiryService;

	@GetMapping
	public ResponseEntity<GetAlICustomerCenterInquiryResponseDto> getAllInquiry(){
		return ResponseEntity.ok().body(customerInquiryService.getAllInquiry());
	}

	@PostMapping
	public ResponseEntity<UUID> createCustomerInquiry(
		@RequestBody CreateCustomerInquiryRequestDto createCustomerInquiryRequestDto){
		return ResponseEntity.ok()
			.body(customerInquiryService.postCustomerInquiry(createCustomerInquiryRequestDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetCustomerInquiryResponseDto> getInquiry(
		@PathVariable UUID id){
		return ResponseEntity.ok().body(customerInquiryService.getCustomerInquiry(id));
	}


}
