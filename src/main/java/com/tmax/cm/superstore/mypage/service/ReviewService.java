package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.repository.ItemRepository;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.user.error.exception.EmailNotFoundException;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
	public GetAllReviewResponseDto getAllReview(){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		List<Review> reviews = reviewRepository.findByUserId(user.getId());
		// List<Review> reviews = reviewRepository.findByUserId(UUID.fromString("672ffb8c-f952-49ec-b65b-4fe3a9c37b28"));
		return GetAllReviewResponseDto.builder()
				.reviews(reviewMapper.toReviewsDto(reviews)).build();
	}

	@Transactional(readOnly = true)
	public GetAllReviewResponseDto getAllReview(UUID itemId){
		List<Review> reviews = reviewRepository.findAllByItemId(itemId);
		return GetAllReviewResponseDto.builder()
				.reviews(reviewMapper.toReviewsDto(reviews)).build();
	}

	@Transactional(readOnly = true)
	public GetAllReviewForSellerResponseDto getAllReviewForSeller(Long filterDay){
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// sellerRepository.findByEmail(email);
		// List<Review> reviews = reviewRepository.findAllBySellerId(seller.getSellerId(), LocalDateTime.now().minusDays(filterDay));
		List<Review> reviews = null;
		return GetAllReviewForSellerResponseDto.builder()
			.reviews(reviewMapper.toReviewsForSellerDto(reviews)).build();
	}

	@Transactional(readOnly = true)
	public GetReviewResponseDto getReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		return GetReviewResponseDto.builder()
			.review(reviewMapper.toReviewDto(review)).build();
	}

	@Transactional
	public UUID postReview(PostReviewRequestDto dto){
		String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findUserByEmail(email).orElseThrow(EmailNotFoundException::new);
		PickupOrderItem orderItem = orderItemRepository.findById(dto.getOrderItemId()).orElseThrow(ItemNotFoundException::new);
		Review review = Review.ReviewBuilder()
				.orderItem(orderItem)
			.title(dto.getTitle())
			.content(dto.getContent())
			.starRating(dto.getStarRating())
			.user(user)
			.build();
		return reviewRepository.save(review).getId();
	}

	@Transactional
	public void updateReview(UpdateReviewRequestDto dto){
		Review review = reviewRepository.findById(dto.getId()).orElseThrow(ReviewNotFoundException::new);
		review.updateReview(dto);
	}

	@Transactional
	public void deleteReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		review.deleteReviewReply();
		reviewRepository.delete(review); // 리뷰 ID 리턴
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
