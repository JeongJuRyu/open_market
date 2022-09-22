package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.service.OrderInquiryReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order-inquiry-reply")
public class OrderInquiryReplyController {
	private final OrderInquiryReplyService orderInquiryReplyService;

	@PostMapping
	public ResponseEntity<Object> postOrderInquiryReply(PostOrderInquiryReplyRequestDto postOrderInquiryReplyRequestDto){
		orderInquiryReplyService.postOrderInquiryReply(postOrderInquiryReplyRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@PatchMapping
	public ResponseEntity<Object> updateOrderInquiryReply(UpdateOrderInquiryReplyRequestDto updateOrderInquiryReplyRequestDto){
		orderInquiryReplyService.updateOrderInquiryReply(updateOrderInquiryReplyRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteOrderInquiryReply(UUID orderInquiryId){
		orderInquiryReplyService.deleteOrderInquiryReply(orderInquiryId);
		return ResponseEntity.ok().body(null);
	}
}
