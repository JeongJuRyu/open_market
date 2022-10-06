package com.tmax.cm.superstore.user.dto;

import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
	private String email;
	private String password;
	private String newPassword;
}
