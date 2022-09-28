package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping
	public ResponseEntity<GetAllReviewResponseDto> getAllReview(){
		return ResponseEntity.ok().body(reviewService.getAllReview());
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<GetReviewResponseDto> getReview(
		@PathVariable UUID reviewId){
		return ResponseEntity.ok().body(reviewService.getReview(reviewId));
	}

	@PostMapping
	public ResponseEntity<UUID> createReview(
		@RequestBody @Valid PostReviewRequestDto postReviewRequestDto) {
		System.out.println(postReviewRequestDto.getReviewImages());
		return ResponseEntity.ok().body(reviewService.postReview(postReviewRequestDto));
	}
	@PatchMapping
	public ResponseEntity<UUID> updateReview(
		@RequestBody UpdateReviewRequestDto updateReviewRequestDto){
		return ResponseEntity.ok().body(reviewService.updateReview(updateReviewRequestDto));
	}

	@DeleteMapping("/{reviewId}")
	public void deleteReview(
		@PathVariable UUID reviewId){
		reviewService.deleteReview(reviewId);
	}
	@GetMapping("/getAll")
	public ResponseEntity<GetAllReviewResponseDto> getAllReviewByItemId(@RequestParam UUID itemId){
		return ResponseEntity.ok().body(reviewService.getAllReview(itemId));
	}

	@GetMapping("/seller")
	public ResponseEntity<GetAllReviewForSellerResponseDto> getAllReviewForSeller(@RequestParam Long filterDay){
		return ResponseEntity.ok().body(reviewService.getAllReviewForSeller(filterDay));
	}
}
