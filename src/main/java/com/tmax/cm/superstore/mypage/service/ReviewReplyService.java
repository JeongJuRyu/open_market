package com.tmax.cm.superstore.mypage.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.dto.PostReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.repository.ReviewReplyRepository;
import com.tmax.cm.superstore.mypage.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewReplyService {
	private final ReviewRepository reviewRepository;
	private final ReviewReplyRepository reviewReplyRepository;


	@Transactional
	public UUID postReviewReply(PostReviewReplyRequestDto dto){
		Review review = reviewRepository.findReviewById(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
		review.setReviewReply(dto);
		return reviewRepository.save(review).getId();
	}

	@Transactional
	public UUID updateReviewReply(UpdateReviewReplyRequestDto dto){
		Review review = reviewRepository.findReviewById(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
		review.updateReviewReply(dto);
		return reviewRepository.save(review).getId();
	}

	public UUID deleteReviewReply(UUID reviewId) {
		Review review = reviewRepository.findReviewById(reviewId).orElseThrow(ReviewNotFoundException::new);
		ReviewReply reviewReply = reviewReplyRepository.findByReview(reviewId);
		review.deleteReviewReply();
		reviewReplyRepository.delete(reviewReply);
		return reviewReply.getId();
	}
}
