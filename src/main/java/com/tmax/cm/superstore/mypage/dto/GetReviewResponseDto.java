package com.tmax.cm.superstore.mypage.dto;

import java.util.List;
import java.util.UUID;

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

		private String title;
		private String content;
		private List<ReviewImage> reviewImages;

		@Getter
		@Builder
		public static class ReviewImage {
			private String url;
		}
		private ReviewReply reviewReply;

		@Getter
		@Builder
		public static class ReviewReply{
			private String content;
		}
	}
}
