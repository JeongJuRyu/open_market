package com.tmax.cm.superstore.mypage.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.order.entity.OrderOption;
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
		private OrderType orderType;
		private String content;
		private LocalDateTime createdAt;
		private Float starRating;
		private OrderItem orderItem;
		@Getter
		@Builder
		public static class OrderItem {
			private String name;
			private Integer price;
			private Integer count;
			private List<OrderOptionGroup> orderOptionGroups;

			@Getter
			@Builder
			public static class OrderOptionGroup {
				private String name;
				private List<OrderOption> orderOptions;

				@Getter
				@Builder
				public static class OrderOption {
					private String name;
					private Long count;
					private Long price;
				}
			}
		}
		private ReviewReply reviewReply;

		@Getter
		@Builder
		public static class ReviewReply{
			private String content;
		}
	}
}
