// package com.tmax.cm.superstore.mypage.dto;
//
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.UUID;
//
// import lombok.Builder;
// import lombok.Getter;
//
// @Getter
// @Builder
// public class GetAlICustomerInquiryResponseDto {
// 	private List<Inquiry> inquiries;
// 	@Getter
// 	@Builder
// 	public static class Inquiry {
// 		private UUID id;
// 		private InquiryItem inquiryItem;
// 		private LocalDateTime inquiryDate;
// 		private boolean replyStatus;
// 		private String content;
// 		@Getter
// 		@Builder
// 		public static class InquiryItem {
// 			private UUID id;
// 			private String imageUrl;
// 			private String name;
// 			private String shopName;
// 		}
// 	}
// }
