package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostItemInquiryReplyRequestDto {
	private UUID itemInquiryId;
	private String content;
}
