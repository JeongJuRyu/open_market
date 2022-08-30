package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class GetAllItemInquiryResponseDto {
	private List<ItemInquiry> itemInquiries;
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ItemInquiry {
		private UUID id;

		private String title;

		private String content;

		private List<ItemInquiryImage> itemInquiryImages;

		private List<ItemInquiryAnswer> itemInquiryAnswers;

		private LocalDateTime createdAt;

		@Getter
		@Builder
		public static class ItemInquiryImage {
			private String url;
		}

		@Getter
		@Builder
		public static class ItemInquiryAnswer {
			private String content;
		}
	}
}
