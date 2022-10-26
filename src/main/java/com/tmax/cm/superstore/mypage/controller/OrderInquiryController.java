package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.service.OrderInquiryService;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orderInquiry")
public class OrderInquiryController {
	private final OrderInquiryService orderInquiryService;

	@GetMapping
	public ResponseEntity<ResponseDto<GetAllOrderInquiryResponseDto>> getAllInquiry(
		@AuthenticationPrincipal User user,
		@RequestParam(defaultValue = "1900-01-01") String startDate,
		@RequestParam(defaultValue = "all") String isReplied
	){
		return ResponseEntity.ok().body(orderInquiryService.getAllOrderInquiry(user, startDate, isReplied));
	}
	@GetMapping("/seller")
	public ResponseEntity<ResponseDto<GetAllOrderInquiryForSellerResponseDto>> getAllInquiryForSeller(
		@RequestParam UUID sellerId,
		@RequestParam(defaultValue = "1900-01-01") String startDate,
		@RequestParam(defaultValue = "all") String isReplied
	){
		return ResponseEntity.ok().body(orderInquiryService.getAllOrderInquiryForSeller(startDate, isReplied, sellerId));
	}

	@GetMapping("/shippingAndDelivery/{orderInquiryId}")
	public ResponseEntity<ResponseDto<GetOrderInquiryResponseDto>> getOrderInquiry(
		@PathVariable UUID orderInquiryId){
		return ResponseEntity.ok().body(orderInquiryService.getShippingOrderInquiry(orderInquiryId));
	}

	@GetMapping("/pickupAndVisit/{pickupOrderSelectedId}")
	public ResponseEntity<ResponseDto<GetOrderInquiryResponseDto>> getPickupOrderReview(
		@PathVariable UUID pickupOrderSelectedId){
		return ResponseEntity.ok().body(orderInquiryService.getPickupOrderInquiry(pickupOrderSelectedId));
	}

	@PostMapping
	public ResponseEntity<Object> postInquiry(
		@RequestBody PostOrderInquiryRequestDto postOrderInquiryRequestDto,
		@AuthenticationPrincipal User user) throws Exception {
		return ResponseEntity.ok()
			.body(orderInquiryService.postOrderInquiry(postOrderInquiryRequestDto, user));
	}

	@PatchMapping
	public ResponseEntity<Object> updateInquiry(
		@RequestBody UpdateOrderInquiryRequestDto updateOrderInquiryRequestDto
	) {
		return ResponseEntity.ok()
			.body(orderInquiryService.updateOrderInquiry(updateOrderInquiryRequestDto));
	}

	@DeleteMapping("/{orderItemInquiryId}")
	public ResponseEntity<Object> deleteInquiry(@PathVariable UUID orderItemInquiryId){
		return ResponseEntity.ok()
			.body(orderInquiryService.deleteOrderInquiry(orderItemInquiryId));
	}
}
