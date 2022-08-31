package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetOrderInquiryResponseDto {
	private String title;

	private String content;

	private LocalDateTime createdAt;
}
