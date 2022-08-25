package com.tmax.cm.superstore.mypage.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UpdateReviewRequestDto {
	private UUID id;
	private String title;
	private String content;
	private List<CreateReviewRequestDto.ReviewImage> reviewImages;

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReviewImage {
		private String url;
	}
}
