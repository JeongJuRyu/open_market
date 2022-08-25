package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.dto.CreateReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.CreateReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewImage;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;
import com.tmax.cm.superstore.mypage.mapper.ReviewMapper;
import com.tmax.cm.superstore.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;

	public GetAllReviewResponseDto getAllReview(GetAllReviewRequestDto dto){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Review> reviewList = reviewRepository.findAllByUserId(user.getId());
		return GetAllReviewResponseDto.builder()
			.reviews(reviewMapper.toReviewDto(reviewList)).build();
	}

	public GetReviewResponseDto getReview(UUID reviewId){
		Review review = reviewRepository.findReviewById(reviewId).orElseThrow(ReviewNotFoundException::new);
		return GetReviewResponseDto.builder()
			.review(reviewMapper.toReviewDto(review)).build();
	}
	@Transactional
	public UUID postReview(CreateReviewRequestDto dto){
		Review review = Review.ReviewBuilder()
			.title(dto.getTitle())
			.content(dto.getContent())
			.build();
		for(CreateReviewRequestDto.ReviewImage reviewImage : dto.getReviewImages()){
			review.getReviewImages().add(ReviewImage.ReviewImageBuilder().url(reviewImage.getUrl()).review(review).build());
		}
		return reviewRepository.save(review).getId();
	}

	@Transactional
	public UUID updateReview(UpdateReviewRequestDto dto){
		Review review = reviewRepository.findReviewById(dto.getId()).orElseThrow(ReviewNotFoundException::new);
		review.updateReview(dto);
		return review.getId();
	}
	@Transactional
	public UUID postReviewReply(CreateReviewReplyRequestDto dto){
		Review review = reviewRepository.findReviewById(dto.getReviewId()).orElseThrow(ReviewNotFoundException::new);
		review.setReviewReply(dto);
		return reviewRepository.save(review).getId();
	}

	@Transactional
	public void deleteReview(UUID reviewId){
		Review review = reviewRepository.findReviewById(reviewId).orElseThrow(ReviewNotFoundException::new);
		review.getReviewImages().clear();
		review.removeReviewReply();
		reviewRepository.delete(review); // 리뷰 ID 리턴
	}
}
