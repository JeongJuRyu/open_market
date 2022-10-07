// package com.tmax.cm.superstore.mypage.controller;
//
// import java.util.UUID;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.tmax.cm.superstore.mypage.dto.PostCustomerInquiryReplyRequestDto;
// import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryReplyRequestDto;
// import com.tmax.cm.superstore.mypage.service.CustomerInquiryReplyService;
//
// import lombok.RequiredArgsConstructor;
//
// @RestController
// @RequiredArgsConstructor
// @RequestMapping("/v1/customer-inquiry-reply")
// public class CustomerInquiryReplyController {
// 	private final CustomerInquiryReplyService customerInquiryReplyService;
//
// 	@PostMapping
// 	public ResponseEntity<Object> postCustomerInquiryReply(PostCustomerInquiryReplyRequestDto postCustomerInquiryReplyRequestDto){
// 		customerInquiryReplyService.postReply(postCustomerInquiryReplyRequestDto);
// 		return ResponseEntity.ok().body(null);
// 	}
//
// 	@PatchMapping
// 	public ResponseEntity<Object> updateCustomerInquiryReply(UpdateCustomerInquiryReplyRequestDto updateCustomerInquiryReplyRequestDto){
// 		customerInquiryReplyService.updateReply(updateCustomerInquiryReplyRequestDto);
// 		return ResponseEntity.ok().body(null);
// 	}
//
// 	@DeleteMapping
// 	public ResponseEntity<Object> deleteCustomerInquiryReply(UUID customerInquiryReplyId){
// 		customerInquiryReplyService.deleteReply(customerInquiryReplyId);
// 		return ResponseEntity.ok().body(null);
// 	}
// }
