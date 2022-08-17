package com.tmax.cm.superstore.mypage.service;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.repository.ItemInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemInquiryService {
	private final ItemInquiryRepository inquiryRepository;
}
