package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class PostReviewReplyRequestDto {
	private UUID reviewId;
	private String content;
}
