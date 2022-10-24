package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.mapper.ReviewMapper;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.repository.PickupOrderItemRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderItemRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderSelectedOptionRepository;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

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
	private final PickupOrderItemRepository pickupOrderItemRepository;
	private final ShippingOrderItemRepository shippingOrderItemRepository;
	private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
	private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
	private final ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetAllReviewResponseDto> getAllReview(String startDate, Boolean isReplied, User user){
		List<GetAllReviewResponseDto.Review> responseReview = new ArrayList<>();

		List<Review> reviews = reviewRepository.findByUserId(user.getId(), LocalDate.parse(startDate).atStartOfDay(), isReplied);
		for(Review review : reviews){
			OrderType orderType = review.getOrderType();
			if(orderType == OrderType.DELIVERY || orderType == OrderType.SHIPPING){
				ShippingOrderItem shippingOrderItem = shippingOrderItemRepository.findByShippingOrderSelectedOptions(
					review.getShippingOrderSelectedOption()).get();

				responseReview.add(reviewMapper.toAllShippingReviewDto(review, shippingOrderItem));
			} else if(orderType == OrderType.VISIT || orderType == OrderType.PICKUP){
				PickupOrderItem pickupOrderItem = pickupOrderItemRepository.findByPickupOrderSelectedOptions(
					review.getPickupOrderSelectedOption()).get();
			}
		}
		return ResponseDto.<GetAllReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ_ALL)
			.data(GetAllReviewResponseDto.builder()
				.reviews(responseReview).build())
			.build();
	}

	// @Transactional(readOnly = true)
	// public GetAllReviewResponseDto getAllReview(UUID itemId){
	// 	List<Review> reviews = reviewRepository.findAllByItemId(itemId);
	// 	return GetAllReviewResponseDto.builder()
	// 			.reviews(reviewMapper.toReviewsDto(reviews)).build();
	// }

	// @Transactional(readOnly = true)
	// public GetAllReviewForSellerResponseDto getAllReviewForSeller(LocalDate startDate){
	// 	// seller의 형식이 정해져 있지 않아서 기다려야 함
	// 	// String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// 	// sellerRepository.findByEmail(email);
	// 	// List<Review> reviews = reviewRepository.findAllBySellerId(seller.getSellerId(), LocalDateTime.now().minusDays(filterDay));
	// 	List<Review> reviews = null;
	// 	return GetAllReviewForSellerResponseDto.builder()
	// 		.reviews(reviewMapper.toReviewsForSellerDto(reviews)).build();
	// }

	@Transactional(readOnly = true)
	public ResponseDto<GetReviewResponseDto> getReview(UUID reviewId){
		Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
		OrderType orderType = review.getOrderType();
		GetReviewResponseDto response = null;
		if(orderType == OrderType.SHIPPING || orderType == OrderType.DELIVERY){
			ShippingOrderItem shippingOrderItem = shippingOrderItemRepository.findByShippingOrderSelectedOptions(
				review.getShippingOrderSelectedOption()).get();
			response = GetReviewResponseDto.builder()
				.review(reviewMapper.toShippingReviewDto(review, shippingOrderItem))
				.build();
		}
		else if(orderType == OrderType.PICKUP || orderType == OrderType.VISIT) {
			PickupOrderItem pickupOrderItem = pickupOrderItemRepository.findByPickupOrderSelectedOptions(
				review.getPickupOrderSelectedOption()).get();
			response = GetReviewResponseDto.builder()
				// .review(reviewMapper.toPickupReviewDto(review, pickupOrderItem))
				.build();
		}

		return ResponseDto.<GetReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ)
			.data(response)
			.build();
	}

	@Transactional
	public ResponseDto<Object> postReview(PostReviewRequestDto dto, User user){
		if(dto.getOrderType() == OrderType.SHIPPING || dto.getOrderType() == OrderType.DELIVERY){
			Item item = itemRepository.findByShippingOrderSelectedOption(dto.getSelectedOptionId())
				.orElseThrow(ItemNotFoundException::new);
			ShippingOrderSelectedOption shippingOrderSelectedOption = shippingOrderSelectedOptionRepository.findById(dto.getSelectedOptionId()).get();
			Review review = Review.builder()
				.user(user)
				.content(dto.getContent())
				.starRating(dto.getStarRating())
				.orderType(dto.getOrderType())
				.shippingOrderSelectedOption(shippingOrderSelectedOption)
				.item(item)
				.build();
			reviewRepository.save(review);
		} else if(dto.getOrderType() == OrderType.PICKUP || dto.getOrderType() == OrderType.VISIT) {
			Item item = itemRepository.findByPickupOrderSelectedOption(dto.getSelectedOptionId())
				.orElseThrow(ItemNotFoundException::new);
			PickupOrderSelectedOption pickupOrderSelectedOption = pickupOrderSelectedOptionRepository.findById(dto.getSelectedOptionId()).get();
			Review review = Review.builder()
				.user(user)
				.content(dto.getContent())
				.starRating(dto.getStarRating())
				.orderType(dto.getOrderType())
				.pickupOrderSelectedOption(pickupOrderSelectedOption)
				.item(item)
				.build();
			reviewRepository.save(review);
		}
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
		reviewRepository.delete(review);
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
