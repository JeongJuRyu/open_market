package com.tmax.cm.superstore.mypage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.service.OrderInquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orderinquiry")
public class OrderInquiryController {
	private final OrderInquiryService orderInquiryService;
}
