package com.tmax.cm.superstore.mypage.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.mypage.dto.PostItemInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateItemInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.service.ItemInquiryReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/item-inquiry-reply")
public class ItemInquiryReplyController {
	private ItemInquiryReplyService itemInquiryReplyService;

	@PostMapping
	public ResponseEntity<Object> postItemInquiryReply(PostItemInquiryReplyRequestDto postItemInquiryReplyRequestDto){
		itemInquiryReplyService.postItemInquiryReply(postItemInquiryReplyRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@PatchMapping
	public ResponseEntity<Object> updateItemInquiryReply(UpdateItemInquiryReplyRequestDto updateItemInquiryReplyRequestDto){
		itemInquiryReplyService.updateItemInquiryReply(updateItemInquiryReplyRequestDto);
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteItemInquiryReply(UUID itemInquiryReplyId){
		itemInquiryReplyService.deleteItemInquiryReply(itemInquiryReplyId);
		return ResponseEntity.ok().body(null);
	}
}
