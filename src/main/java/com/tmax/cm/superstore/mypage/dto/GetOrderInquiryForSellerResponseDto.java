package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class GetOrderInquiryForSellerResponseDto {

	private OrderInquiry orderInquiry;
	private OrderInquiryReply orderInquiryReply;
	@Getter
	@Builder
	public static class OrderInquiry {
		private LocalDateTime CreatedAt;
		private Boolean isReplied;
		private UUID orderId;
		private String content;
		private UUID orderItemId;
		private String itemName;
		private String name;
		private LocalDateTime processedAt;
	}
	@Getter
	@Builder
	public static class OrderInquiryReply{
		private String content;
	}
}
