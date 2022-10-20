package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

@Getter
@Builder
public class PostReviewRequestDto {
    private String content;
    @NotNull
    private Float starRating;

    @NotNull
    private OrderType orderType;

    @NotNull
    private UUID selected_option_id;
}
