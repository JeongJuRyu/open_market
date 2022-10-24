package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetOrderInquiryResponseDto {
	private OrderItemInquiry orderInquiry;

	@Getter
	@Builder
	public static class OrderItemInquiry {
		private UUID id;
		private String shopName;
		private String orderItemName;
		private Boolean isReplied;
		private String content;
		private LocalDateTime createdAt;
	}

	private OrderItemInquiryReply orderInquiryReply;

	@Getter
	@Builder
	public static class OrderItemInquiryReply {
		private String content;
		private LocalDateTime createdAt;
	}
}
