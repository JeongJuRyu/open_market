package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllOrderInquiryResponseDto {
	private List<OrderInquiry> orderInquiries;

	@Getter
	@Builder
	public static class OrderInquiry {
		private UUID id;
		private String title;
		private LocalDateTime createdAt;
		private Boolean isAnswered;
	}
}
