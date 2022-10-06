package com.tmax.cm.superstore.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserInfoResponseDto {
	private String name;
	private String email;
	private String phoneNum;
}
