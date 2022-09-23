package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllCustomerInquiryForSellerResponseDto {
	private List<CustomerInquiry> customerInquiries;
	@Getter
	@Builder
	public static class CustomerInquiry{
		private UUID customerInquiryId;
		private LocalDateTime createdAt;
		private Boolean isProcessed;
		private UUID orderItemId;
		private String title;
		private String itemName;
		private String customerName;
	}
}
