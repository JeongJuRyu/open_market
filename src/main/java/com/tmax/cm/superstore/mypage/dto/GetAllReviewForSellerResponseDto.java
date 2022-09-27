package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetAllReviewForSellerResponseDto {
	private List<Review> reviews;
	@Getter
	@Builder
	public static class Review {
		private String itemName;
		private Float starRating;
		private List<ReviewImage> reviewImages;
		private String content;
		private Long isUseful;
		private LocalDateTime createdAt;
		private LocalDateTime modifiedAt;

		@Getter
		@Builder
		public static class ReviewImage {
			private String url;
		}
	}
}
