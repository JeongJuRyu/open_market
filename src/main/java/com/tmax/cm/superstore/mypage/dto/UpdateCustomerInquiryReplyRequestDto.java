package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCustomerInquiryReplyRequestDto {
	private UUID customerInquiryId;
	private UUID customerInquiryReplyId;
	private String title;
	private String content;
}
