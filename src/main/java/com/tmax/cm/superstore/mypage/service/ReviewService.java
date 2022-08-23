package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.mypage.dto.GetAllReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewImage;
import com.tmax.cm.superstore.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public GetAllReviewResponseDto getAllReview(GetAllReviewRequestDto dto){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Review> reviewList = reviewRepository.findAllByUserId(user.getId());
		return null;
	}
}
