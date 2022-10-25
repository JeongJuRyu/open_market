package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllReviewForSellerResponseDto {
	private List<Review> reviews;
	@Getter
	@Builder
	public static class Review {
		private UUID reviewId;
		private UUID orderItemId;
		private UUID orderId;
		private String itemName;
		private Float starRating;
		private String content;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;
	}
}
