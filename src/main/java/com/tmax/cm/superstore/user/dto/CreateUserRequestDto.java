package com.tmax.cm.superstore.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequestDto {
	@NotNull
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;

	@NotNull
	private String userPhoneNum;

	@NotNull
	private String password;

	@NotNull
	private String address;

	@NotNull
	private String name;
}
