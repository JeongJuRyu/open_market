package com.tmax.cm.superstore.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateEmailResponseDto {
	private String email;
	private String newEmail;
}
