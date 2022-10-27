package com.tmax.cm.superstore.mypage.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
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
	public ResponseDto<Object> postReviewReply(PostReviewReplyRequestDto dto){
		Review review = reviewRepository.findById(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
		review.setReviewReply(dto);
		reviewRepository.save(review);
		return ResponseDto.builder().responseCode(ResponseCode.REVIEW_REPLY_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> updateReviewReply(UpdateReviewReplyRequestDto dto){
		Review review = reviewRepository.findByIdWithReply(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
		ReviewReply reviewReply = review.getReviewReply();
		reviewReply.updateReviewReply(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.REVIEW_REPLY_UPDATE)
			.data(null).build();
	}

	public ResponseDto<Object> deleteReviewReply(UUID reviewId) {
		Review review = reviewRepository.findByIdWithReply(reviewId).orElseThrow(ReviewNotFoundException::new);
		ReviewReply reviewReply = review.getReviewReply();
		review.deleteReviewReply();
		reviewReplyRepository.delete(reviewReply);
		return ResponseDto.builder()
			.responseCode(ResponseCode.REVIEW_REPLY_DELETE)
			.data(null).build();
	}
}
