package com.tmax.cm.superstore.mypage.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UpdateReviewRequestDto {
	@NotNull
	private UUID id;
	private String content;
	@NotNull
	private Float starRating;
}
