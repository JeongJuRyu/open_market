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
        private UUID id;

        private OrderType orderType;

        // private String itemName;
        //
        // private String optionName;

        // private Integer count;

        private String content;

        private LocalDateTime createdAt;
    }
}
