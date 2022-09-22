package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateItemInquiryReplyRequestDto {
	private UUID itemInquiryID;
	private UUID itemInquiryReplyId;
	private String content;
}
