package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateReviewReplyRequestDto {
	private UUID reviewId;
	private UUID reviewReplyId;
	private String content;
}
