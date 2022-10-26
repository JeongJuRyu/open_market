package com.tmax.cm.superstore.mypage.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewWithItemResponseDto;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
import com.tmax.cm.superstore.mypage.dto.PostReviewRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.mapper.ReviewMapper;
import com.tmax.cm.superstore.mypage.util.ReviewComparotor;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.repository.OrderRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderItemRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderItemRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderSelectedOptionRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.error.exception.BusinessNotFoundException;
import com.tmax.cm.superstore.seller.error.exception.SellerNotFoundException;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;
	private final OrderRepository orderRepository;
	private final ReviewMapper reviewMapper;
	private final ShippingOrderRepository shippingOrderRepository;
	private final PickupOrderRepository pickupOrderRepository;
	private final ShippingOrderItemRepository shippingOrderItemRepository;
	private final PickupOrderItemRepository pickupOrderItemRepository;
	private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
	private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
	private final SellerRepository sellerRepository;
	private final ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetAllReviewResponseDto> getAllReview(User user){
		List<GetAllReviewResponseDto.Review> responseReview = new ArrayList<>();
		List<Review> reviews = reviewRepository.findByUserId(user.getId());
		reviews.sort(new ReviewComparotor());
		for(Review review : reviews){
			OrderType orderType = review.getOrderType();
			if(orderType == OrderType.SHIPPINGANDDELIVERY) {
				UUID selectedOptionId = review.getShippingOrderSelectedOption().getId();
				ShippingOrderItem shippingOrderItem = shippingOrderItemRepository.findByShippingOrderSelectedOptions(
					review.getShippingOrderSelectedOption()).get();
				Item item = shippingOrderItemRepository.findByShippingOrderItemId(shippingOrderItem.getId())
					.orElseThrow(ItemNotFoundException::new).getItem();
				// Item itemWithItemImage = itemRepository.findByItemWithImage(item.getId())
				// 	.orElseThrow(ItemNotFoundException::new);
				responseReview.add(reviewMapper.toAllShippingReviewDto(review, shippingOrderItem, item, selectedOptionId));
			} else if(orderType == OrderType.PICKUPANDVISIT){
				PickupOrderItem pickupOrderItem = pickupOrderItemRepository.findByPickupOrderSelectedOptions(
					review.getPickupOrderSelectedOption()).get();
				Item item = shippingOrderItemRepository.findByShippingOrderItemId(pickupOrderItem.getId())
					.orElseThrow(ItemNotFoundException::new).getItem();
				// responseReview.add(reviewMapper.toAllPickupReviewDto(review, pickupOrderItem, item));
			}
		}
		return ResponseDto.<GetAllReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ_ALL)
			.data(GetAllReviewResponseDto.builder()
				.reviews(responseReview).build())
			.build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetAllReviewForSellerResponseDto> getAllReviewForSeller(UUID sellerId, Float starRating){
		List<GetAllReviewForSellerResponseDto.Review> responseReview = new ArrayList<>();
		List<Review> reviews = sellerRepository.findForSellerReviewWithItem(sellerId);
		reviews = reviews.stream().filter(review -> review.getStarRating() >= starRating).collect(Collectors.toList());
		for(Review review : reviews) {
			Order order = orderRepository.findByReviewId(review.getId())
				.orElseThrow(() -> new RuntimeException("주문이 존재하지 않습니다."));
			responseReview.add(reviewMapper.toAllReviewForSellerDto(review, order));
		}
		return ResponseDto.<GetAllReviewForSellerResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ_ALL)
			.data(GetAllReviewForSellerResponseDto.builder()
				.reviews(responseReview).build())
			.build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetReviewResponseDto> getShippingOrderReview(UUID shippingOrderSelectedId){
		Review review = reviewRepository.findByShippingOrderSelectedOption(shippingOrderSelectedId).orElseThrow(ReviewNotFoundException::new);
		ShippingOrderItem shippingOrderItem = shippingOrderItemRepository.findByShippingOrderSelectedOptions(
			review.getShippingOrderSelectedOption()).get();
		Item item = shippingOrderItemRepository.findByShippingOrderItemId(shippingOrderItem.getId())
			.orElseThrow(ItemNotFoundException::new).getItem();
		// Item itemWithItemImage = itemRepository.findByItemWithImage(item.getId())
		// // 	.orElseThrow(ItemNotFoundException::new);
		// String itemImageId = itemWithItemImage.getItemImages().size() == 0 ? null : itemWithItemImage.getItemImages().get(0).getFileId();
		String itemImageId = null;
		GetReviewResponseDto response = GetReviewResponseDto.builder()
			.review(reviewMapper.toShippingReviewDto(review, shippingOrderItem, item, itemImageId))
			.build();

		return ResponseDto.<GetReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ)
			.data(response)
			.build();
	}
	@Transactional(readOnly = true)
	public ResponseDto<GetReviewResponseDto> getPickupOrderReview(UUID pickupOrderSelectedId){
		Review review = reviewRepository.findByPickupOrderSelectedOption(pickupOrderSelectedId).orElseThrow(ReviewNotFoundException::new);
		PickupOrderItem pickupOrderItem = pickupOrderItemRepository.findByPickupOrderSelectedOptions(
			review.getPickupOrderSelectedOption()).get();
		Item item = pickupOrderItemRepository.findByPickupOrderItemId(pickupOrderItem.getId())
			.orElseThrow(ItemNotFoundException::new).getItem();
		String itemImageId = null;
		GetReviewResponseDto response = GetReviewResponseDto.builder()
			.review(reviewMapper.toPickupReviewDto(review, pickupOrderItem, item, itemImageId))
			.build();

		return ResponseDto.<GetReviewResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ)
			.data(response)
			.build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetReviewWithItemResponseDto> getReviewWithItem(UUID itemId){
		Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
		List<Review> reviews = reviewRepository.findByItem(item);
		List<GetReviewWithItemResponseDto.Review> responseReviews = new ArrayList<>();
		for(Review review : reviews){
			GetReviewWithItemResponseDto.Review responseReview = GetReviewWithItemResponseDto.Review.builder()
				.reviewId(review.getId())
				.userName(review.getUser().getName())
				.createdAt(review.getCreatedAt())
				.content(review.getContent())
				.starRating(review.getStarRating())
				.build();
			responseReviews.add(responseReview);
		}
		return ResponseDto.<GetReviewWithItemResponseDto>builder()
			.responseCode(ResponseCode.REVIEW_READ_ALL)
			.data(GetReviewWithItemResponseDto.builder()
				.reviews(responseReviews)
				.build())
			.build();
	}
	@Transactional
	public ResponseDto<Object> postReview(PostReviewRequestDto dto, User user){
		if(dto.getOrderType() == OrderType.SHIPPINGANDDELIVERY) {
			ShippingOrderSelectedOption shippingOrderSelectedOption = shippingOrderSelectedOptionRepository.findById(dto.getSelectedOptionId()).get();
			ShippingOrderItem shippingOrderItem = shippingOrderItemRepository.findByShippingOrderSelectedOptions(shippingOrderSelectedOption)
				.orElseThrow(BusinessNotFoundException::new);
			System.out.println(shippingOrderItem.getId());
			Item item = shippingOrderItemRepository.findByShippingOrderItemId(shippingOrderItem.getId())
				.orElseThrow(ItemNotFoundException::new).getItem();
			Review review = Review.builder()
				.user(user)
				.content(dto.getContent())
				.starRating(dto.getStarRating())
				.orderType(dto.getOrderType())
				.shippingOrderSelectedOption(shippingOrderSelectedOption)
				.item(item)
				.build();
			reviewRepository.save(review);
		} else if(dto.getOrderType() == OrderType.PICKUPANDVISIT){
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
	public Double getAvgStarRating(Item item){
		// List<Review> reviews = reviewRepository.findAllByItemId(itemId);
		List<Review> reviews = reviewRepository.findByItem(item);
		List<Float> stars = new ArrayList<>();
		for(Review review : reviews){
			stars.add(review.getStarRating());
		}
		return stars.stream().mapToDouble(a->a).average().orElseThrow(ReviewNotFoundException::new);
	}
}
