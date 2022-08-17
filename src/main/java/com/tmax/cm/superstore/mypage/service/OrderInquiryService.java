package com.tmax.cm.superstore.mypage.service;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.repository.OrderInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryService {
	private final OrderInquiryRepository orderInquiryRepository;
}
