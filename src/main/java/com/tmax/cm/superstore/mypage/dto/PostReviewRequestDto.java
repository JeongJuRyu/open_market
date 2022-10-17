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
    private Float starRating;
    @NotNull
    private UUID orderItemId;
}
