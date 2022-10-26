package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostOrderInquiryReplyRequestDto {
	private UUID sellerId;
	private UUID orderInquiryId;
	private String content;
}
