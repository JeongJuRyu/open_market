package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetReviewResponseDto {
	private Review review;

	@Getter
	@Builder
	public static class Review {
		private UUID id;

		// private OrderType orderType;
		//
		// private UUID shippingOrderSelectedOptionId;
		//
		// private UUID pickupOrderSelectedOptionId;
		// private String itemName;
		// //
		// // private String optionName;
		//
		// private Integer count;

		private String content;

		private LocalDateTime createdAt;
		private ReviewReply reviewReply;

		@Getter
		@Builder
		public static class ReviewReply{
			private String content;
		}
	}
}
