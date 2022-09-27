package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllCustomerInquiryForSellerRequestDto {
	private Long filterDay;
	private Boolean isProcessed;
}
