package com.tmax.cm.superstore.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class PostReviewRequestDto {
    private UUID itemId;
    private String title;
    private String content;
    private Float starRating;
    private List<ReviewImage> reviewImages;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewImage {
        private String url;
    }
}
