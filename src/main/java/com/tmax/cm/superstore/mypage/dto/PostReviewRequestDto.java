package com.tmax.cm.superstore.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class PostReviewRequestDto {
    private String title;
    private String content;
    private List<ReviewImage> reviewImages;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewImage {
        private String url;
    }
}
