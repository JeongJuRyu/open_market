package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import com.tmax.cm.superstore.mypage.dto.GetAllReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.user.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping
	public ResponseEntity<GetAllReviewResponseDto> getAllReview(
		@RequestBody GetAllReviewRequestDto getAllReviewRequestDto){
		return ResponseEntity.ok().body(reviewService.getAllReview(getAllReviewRequestDto));
	}

	/*@PostMapping
	public UUID createReview(

	)*/

	@GetMapping("{reviewId}")
	public ResponseEntity<GetReviewResponseDto> getReview(
		@RequestParam UUID reviewId){
		return ResponseEntity.ok().body(reviewService.getReview(reviewId));
	}

}
