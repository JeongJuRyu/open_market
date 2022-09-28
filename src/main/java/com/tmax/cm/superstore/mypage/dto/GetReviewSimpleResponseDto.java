package com.tmax.cm.superstore.mypage.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetReviewSimpleResponseDto {
    Double avgScore;

    Long reviewCount;
}
