package com.tmax.cm.superstore.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PostReviewRequestDto {
    @NotNull
    private String title;
    private String content;
    @NotNull
    private float star_rating;
    @NotNull
    private UUID itemId;
    @NotNull
    private UUID userId;
    private UUID sellerId;
    private List<ReviewImage> reviewImages;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewImage {
        private String url;
    }
}
