package com.tmax.cm.superstore.mypage.service;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
}
