package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetReviewWithItemResponseDto {
	private List<Review> reviews;
	@Getter
	@Builder
	public static class Review {
		private UUID reviewId;
		private String userName;
		private Float starRating;
		private String content;
		private LocalDateTime createdAt;
	}

}
