package com.tmax.cm.superstore.mypage.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.dto.GetAlICustomerCenterInquiryResponseDto;
import com.tmax.cm.superstore.mypage.repository.CustomerCenterInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCenterInquiryService {
	private final CustomerCenterInquiryRepository customerCenterInquiryRepository;

	public GetAlICustomerCenterInquiryResponseDto getAllInquiry(UUID userId){

		return GetAlICustomerCenterInquiryResponseDto.builder().build();
	}
}
