package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import com.tmax.cm.superstore.mypage.dto.CreateReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public ResponseEntity<UUID> createReview(
		@RequestBody CreateReviewRequestDto createReviewRequestDto) {
		return ResponseEntity.ok().body(reviewService.postReview(createReviewRequestDto));
	}

	@GetMapping("{reviewId}")
	public ResponseEntity<GetReviewResponseDto> getReview(
		@PathVariable UUID reviewId){
		return ResponseEntity.ok().body(reviewService.getReview(reviewId));
	}

}
