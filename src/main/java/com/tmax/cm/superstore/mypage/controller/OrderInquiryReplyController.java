package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.service.OrderInquiryReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orderInquiryReply")
public class OrderInquiryReplyController {
	private final OrderInquiryReplyService orderInquiryReplyService;

	@PostMapping
	public ResponseEntity<ResponseDto<Object>> postOrderInquiryReply(@RequestBody PostOrderInquiryReplyRequestDto postOrderInquiryReplyRequestDto){
		return ResponseEntity.ok().body(orderInquiryReplyService.postOrderInquiryReply(postOrderInquiryReplyRequestDto));
	}

	@PatchMapping
	public ResponseEntity<ResponseDto<Object>> updateOrderInquiryReply(@RequestBody UpdateOrderInquiryReplyRequestDto updateOrderInquiryReplyRequestDto){
		return ResponseEntity.ok().body(orderInquiryReplyService.updateOrderInquiryReply(updateOrderInquiryReplyRequestDto));
	}

	@DeleteMapping("/{orderInquiryId}")
	public ResponseEntity<ResponseDto<Object>> deleteOrderInquiryReply(
		@PathVariable UUID orderInquiryId){
		return ResponseEntity.ok().body(orderInquiryReplyService.deleteOrderInquiryReply(orderInquiryId));
	}

	@PostMapping("/seller")
	public ResponseEntity<ResponseDto<Object>> postOrderInquiryReplyForSeller(@RequestBody PostOrderInquiryReplyRequestDto postOrderInquiryReplyRequestDto){
		return ResponseEntity.ok().body(orderInquiryReplyService.postOrderInquiryReply(postOrderInquiryReplyRequestDto));
	}

	@PatchMapping("/seller")
	public ResponseEntity<ResponseDto<Object>> updateOrderInquiryReplyForSeller(@RequestBody UpdateOrderInquiryReplyRequestDto updateOrderInquiryReplyRequestDto){
		return ResponseEntity.ok().body(orderInquiryReplyService.updateOrderInquiryReply(updateOrderInquiryReplyRequestDto));
	}

	@DeleteMapping("/seller/{orderInquiryId}")
	public ResponseEntity<ResponseDto<Object>> deleteOrderInquiryReplyForSeller(
		@PathVariable UUID orderInquiryId){
		return ResponseEntity.ok().body(orderInquiryReplyService.deleteOrderInquiryReply(orderInquiryId));
	}
}
