package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

@Getter
@Builder
public class GetAllReviewResponseDto {
    private List<Review> reviews;
    @Getter
    @Builder
    public static class Review{
        private UUID reviewId;

        private OrderType orderType;

        private String content;

        private Float starRating;

        private LocalDateTime createdAt;

        private OrderItem orderItem;

        @Getter
        @Builder
        public static class OrderItem {

            private String itemName;

            private UUID itemId;

            private String itemImageId;

            private Integer price;

            private Integer count;

            private UUID orderSelectedOptionId;

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

                    private Integer count;

                    private Integer price;
                }
            }
        }
    }
}
