package com.tmax.cm.superstore.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
	@NotNull
	@Email(regexp = ".+[@].+[\\.].+")
	@Size(min = 3, max = 50)
	private String email;

	@NotNull
	@Size(min = 3, max = 100)
	private String password;

	@NotNull
	@Builder.Default
	private boolean forceLogin = false;
}
