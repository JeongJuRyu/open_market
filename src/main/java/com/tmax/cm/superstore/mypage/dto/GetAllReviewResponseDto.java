package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetAllReviewResponseDto {
    private List<ReviewDto> reviews;
    @Getter
    @Builder
    public static class ReviewDto{
        private UUID id;

        private String title;

        private String content;

        private LocalDateTime createdAt;

        private List<ReviewImage> reviewImages;

        @Getter
        @Builder
        public static class ReviewImage{
            private String url;
        }
    }
}
