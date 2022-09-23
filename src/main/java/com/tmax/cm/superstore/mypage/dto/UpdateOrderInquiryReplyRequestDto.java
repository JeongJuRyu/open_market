package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateOrderInquiryReplyRequestDto {
	private UUID orderInquiryId;
	private UUID orderInquiryReplyId;
	private String content;
}
