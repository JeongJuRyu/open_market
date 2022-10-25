package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllOrderInquiryResponseDto {
	private List<OrderItemInquiry> orderInquiries;

	@Getter
	@Builder
	public static class OrderItemInquiry {
		private UUID orderItemInquiryId;
		private String shopName;
		private UUID orderItemId;
		private String orderItemName;
		private String itemImageId;
		private Boolean isReplied;
		private String content;
		private LocalDateTime createdAt;
		private String orderType;
	}
}
