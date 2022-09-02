package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateOrderInquiryRequestDto {
	private UUID id;

	private String title;

	private String content;
}
