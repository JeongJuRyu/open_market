package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PostReviewRequestDto {
    private String content;
    @NotNull
    private Float starRating;
    @NotNull
    private UUID orderItemId;
}
