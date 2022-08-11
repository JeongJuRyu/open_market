package com.tmax.cm.superstore.mypage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.service.MyPageReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class MyPageReviewController {
	private final MyPageReviewService myPageReviewService;
}
