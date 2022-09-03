package com.tmax.cm.superstore.mypage.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllOrderInquiryReplyResponseDto {
	private List<OrderInquiryReply> orderInquiryReplies;

	public static class OrderInquiryReply {
		private UUID id;

	}
}
