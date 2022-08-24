package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateReviewRequestDto {
    private String title;
    private String content;
    private List<ReviewImage> reviewImages;

    @Getter
    @Builder
    public static class ReviewImage {
        private String url;
    }
}
