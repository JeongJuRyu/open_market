package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewImage;
import com.tmax.cm.superstore.mypage.mapper.ReviewMapper;
import com.tmax.cm.superstore.mypage.repository.ReviewReplyRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;
	private final ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public GetAllReviewResponseDto getAllReview(){
		// User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//List<Review> review = reviewRepository.findByUser(user);
		List<Review> reviews = reviewRepository.findByUserId(UUID.fromString("672ffb8c-f952-49ec-b65b-4fe3a9c37b28"));
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
		Seller seller = (Seller) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Review> reviews = reviewRepository.findAllBySellerId(seller.getSellerId(), LocalDateTime.now().minusDays(filterDay));
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
		Item item = itemRepository.findById(dto.getItemId()).orElseThrow(ItemNotFoundException::new);
		Review review = Review.ReviewBuilder()
				.item(item)
			.title(dto.getTitle())
			.content(dto.getContent())
			.build();
		for(PostReviewRequestDto.ReviewImage reviewImage : dto.getReviewImages()){
			review.getReviewImages().add(ReviewImage.ReviewImageBuilder().url(reviewImage.getUrl()).review(review).build());
		}
		return reviewRepository.save(review).getId();
	}

	@Transactional
	public UUID updateReview(UpdateReviewRequestDto dto){
		Review review = reviewRepository.findById(dto.getId()).orElseThrow(ReviewNotFoundException::new);
		review.updateReview(dto);
		return review.getId();
	}

	@Transactional
	public void deleteReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		review.getReviewImages().clear();
		review.deleteReviewReply();
		reviewRepository.delete(review); // 리뷰 ID 리턴
	}
}
