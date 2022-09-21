package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.PostReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.service.ReviewReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reply")
public class ReviewReplyController {
	private final ReviewReplyService reviewReplyService;

	@PostMapping
	public ResponseEntity<UUID> createReviewReply(
		@RequestBody PostReviewReplyRequestDto postReviewReplyRequestDto) {
		return ResponseEntity.ok().body(reviewReplyService.postReviewReply(postReviewReplyRequestDto));
	}

	@PatchMapping
	public ResponseEntity<UUID> updateReviewReply(
		@RequestBody UpdateReviewReplyRequestDto updateReviewReplyRequestDto) {
		return ResponseEntity.ok().body(reviewReplyService.updateReviewReply(updateReviewReplyRequestDto));
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<UUID> deleteReviewReply(@PathVariable UUID reviewId){
		return ResponseEntity.ok().body(reviewReplyService.deleteReviewReply(reviewId));
	}
}
