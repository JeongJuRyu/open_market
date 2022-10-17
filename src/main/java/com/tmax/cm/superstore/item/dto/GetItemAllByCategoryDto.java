package com.tmax.cm.superstore.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.item.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GetItemAllByCategoryDto {
    @Builder
    @Getter
    public static class Response {

        private List<GetItemSimpleDto> items;

        private List<GetSimpleReviewDto> simpleReview;

        @Builder
        @Getter
        public static class GetSimpleReviewDto {
            private Double avgScore;

            private Integer reviewCount;
        }

        @Builder
        @Getter
        public static class GetItemSimpleDto {

            private UUID shopId;

            private String shopName;

            private Set<SendType> possibleSendType;

            private List<GetItemImageDto> itemImages;

            private UUID itemId;

            private String itemName;

            private Integer itemPrice;

            private Long categoryId;

            @Builder
            @Getter
            public static class GetItemImageDto {
                private String fileId;
            }

            private LocalDateTime createdAt;

            private LocalDateTime modifiedAt;

            private ItemState itemState;
        }

    }
}
