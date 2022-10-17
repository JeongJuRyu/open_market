package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.OrderItemRepository;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.mapper.ReviewMapper;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetAllReviewResponseDto> getAllReview(String startDate, Boolean isReplied, User user){
		List<Review> reviews = reviewRepository.findByUserId(user.getId(), LocalDate.parse(startDate), isReplied);
		return ResponseDto.<GetAllReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ_ALL)
			.data(GetAllReviewResponseDto.builder()
				.reviews(reviewMapper.toReviewsDto(reviews)).build())
			.build();
	}

	@Transactional(readOnly = true)
	public GetAllReviewResponseDto getAllReview(UUID itemId){
		List<Review> reviews = reviewRepository.findAllByItemId(itemId);
		return GetAllReviewResponseDto.builder()
				.reviews(reviewMapper.toReviewsDto(reviews)).build();
	}

	@Transactional(readOnly = true)
	public GetAllReviewForSellerResponseDto getAllReviewForSeller(LocalDate startDate){
		// seller의 형식이 정해져 있지 않아서 기다려야 함
		// String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// sellerRepository.findByEmail(email);
		// List<Review> reviews = reviewRepository.findAllBySellerId(seller.getSellerId(), LocalDateTime.now().minusDays(filterDay));
		List<Review> reviews = null;
		return GetAllReviewForSellerResponseDto.builder()
			.reviews(reviewMapper.toReviewsForSellerDto(reviews)).build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetReviewResponseDto> getReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		return ResponseDto.<GetReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ)
			.data(GetReviewResponseDto.builder()
				.review(reviewMapper.toReviewDto(review)).build())
			.build();
	}

	@Transactional
	public ResponseDto<Object> postReview(PostReviewRequestDto dto, User user){
		PickupOrderItem orderItem = orderItemRepository.findById(dto.getOrderItemId()).orElseThrow(ItemNotFoundException::new);
		Review review = Review.ReviewBuilder()
				.orderItem(orderItem)
			.title(dto.getTitle())
			.content(dto.getContent())
			.starRating(dto.getStarRating())
			.user(user)
			.build();
		reviewRepository.save(review);
		return ResponseDto.builder()
			.responseCode(ResponseCode.REVIEW_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> updateReview(UpdateReviewRequestDto dto){
		Review review = reviewRepository.findById(dto.getId()).orElseThrow(ReviewNotFoundException::new);
		review.updateReview(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.REVIEW_UPDATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> deleteReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		review.deleteReviewReply();
		reviewRepository.delete(review); // 리뷰 ID 리턴
		return ResponseDto.builder()
			.responseCode(ResponseCode.REVIEW_DELETE)
			.data(null).build();
	}

	@Transactional
	public Double getAvgStarRating(UUID itemId){
		List<Review> reviews = reviewRepository.findAllByItemId(itemId);
		List<Float> stars = new ArrayList<>();
		for(Review review : reviews){
			stars.add(review.getStarRating());
		}
		return stars.stream().mapToDouble(a->a).average().orElseThrow(ReviewNotFoundException::new);
	}
}
