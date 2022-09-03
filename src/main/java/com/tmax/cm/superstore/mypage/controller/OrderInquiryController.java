package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.service.OrderInquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orderInquiry")
public class OrderInquiryController {
	private final OrderInquiryService orderInquiryService;

	@GetMapping
	public ResponseEntity<GetAllOrderInquiryResponseDto> getAllInquiry(){
		return ResponseEntity.ok().body(orderInquiryService.getAllOrderInquiry());
	}

	@GetMapping("/{inquiryId}")
	public ResponseEntity<GetOrderInquiryResponseDto> getInquiry(@RequestParam UUID inquiryId){
		return ResponseEntity.ok().body(orderInquiryService.getOrderInquiry(inquiryId));
	}

	@PostMapping
	public ResponseEntity<UUID> postInquiry(
		@RequestBody PostOrderInquiryRequestDto postOrderInquiryRequestDto){
		return ResponseEntity.ok()
			.body(orderInquiryService.postOrderInquiry(postOrderInquiryRequestDto));
	}

	@PatchMapping
	public ResponseEntity<UUID> updateInquiry(
		@RequestBody UpdateOrderInquiryRequestDto updateOrderInquiryRequestDto
	) {
		return ResponseEntity.ok()
			.body(orderInquiryService.updateOrderInquiry(updateOrderInquiryRequestDto));
	}

	@DeleteMapping("/{inquiryId}")
	public ResponseEntity<UUID> deleteInquiry(@RequestParam UUID inquiryId){
		return ResponseEntity.ok()
			.body(orderInquiryService.deleteOrderInquiry(inquiryId));
	}
}
