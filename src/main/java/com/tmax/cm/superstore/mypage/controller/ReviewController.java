package com.tmax.cm.superstore.mypage.controller;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.Valid;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.service.ReviewService;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping
	public ResponseEntity<ResponseDto<GetAllReviewResponseDto>> getAllReview(
		@RequestParam(defaultValue = "1900-01-01") String startDate,
		@RequestParam(defaultValue = "false") Boolean isReplied,
		@AuthenticationPrincipal User user){
		return ResponseEntity.ok().body(reviewService.getAllReview(startDate, isReplied, user));
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<ResponseDto<GetReviewResponseDto>> getReview(
		@PathVariable UUID reviewId){
		return ResponseEntity.ok().body(reviewService.getReview(reviewId));
	}

	@PostMapping
	public ResponseEntity<ResponseDto<Object>> createReview(
		@Valid @RequestBody PostReviewRequestDto postReviewRequestDto,
		@AuthenticationPrincipal User user) {
		return ResponseEntity.ok().body(reviewService.postReview(postReviewRequestDto, user));
	}

	@PatchMapping
	public ResponseEntity<ResponseDto<Object>> updateReview(
		@Valid @RequestBody UpdateReviewRequestDto updateReviewRequestDto){
		return ResponseEntity.ok().body(reviewService.updateReview(updateReviewRequestDto));
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<ResponseDto<Object>> deleteReview(
		@PathVariable UUID reviewId){
		return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
	}
	@GetMapping("/getAll")
	public ResponseEntity<GetAllReviewResponseDto> getAllReviewByItemId(@RequestParam UUID itemId){
		return ResponseEntity.ok().body(reviewService.getAllReview(itemId));
	}

	@GetMapping("/seller")
	public ResponseEntity<GetAllReviewForSellerResponseDto> getAllReviewForSeller(
		@RequestParam LocalDate startDate){
		return ResponseEntity.ok().body(reviewService.getAllReviewForSeller(startDate));
	}
}
