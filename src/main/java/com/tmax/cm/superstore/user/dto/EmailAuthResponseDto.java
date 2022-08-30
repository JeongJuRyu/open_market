package com.tmax.cm.superstore.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailAuthResponseDto {
	private String validNum;
}
