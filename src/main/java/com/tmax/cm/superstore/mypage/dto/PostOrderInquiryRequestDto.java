package com.tmax.cm.superstore.mypage.dto;

import java.util.UUID;

import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostOrderInquiryRequestDto {
	private String content;

	private OrderType orderType;

	private UUID selectedOptionId;
}
