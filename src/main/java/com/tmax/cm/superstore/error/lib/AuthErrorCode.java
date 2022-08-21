package com.tmax.cm.superstore.error.lib;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthErrorCode {

	// Common
	UNAUTHORIZED("JJ000", "Unauthorized"),
	INVALID_USER("JJ001", "Invalid user"),
	INVALID_ACCESS_TOKEN("JJ002", "Invalid Access Token, maybe token is expired"),
	CHECK_AUTHORIZATION_HEADER("JJ003", "Invalid JWT. Check Authorization Header, We need Bearer Token"),
	INVALID_REFRESH_TOKEN("JJ004", "Invalid Refresh Token, maybe token is expired"),
	NEED_ACCESS_TOKEN("JJ005", "Need Access Token to reissue Authorization"),
	NEED_REFRESH_TOKEN("JJ006", "Need Access Token to reissue Authorization"),
	NEED_CREDENTIAL("JJ007", "Need Credential Information"),
	ALREADY_LOGIN_OTHER_DEVICES("JJ008", "Already Logged In Other Devices"),
	LOGOUT_FAIL("JJ009", "Check access token to logout"),
	ALREADY_LOGOUT("JJ010", "Already logged out"),
	TRY_WRONG_PASSWORD_TOO_MUCH("JJ011", "Try wrong Password too much"),
	NOT_CERTIFIED("JJ012", "Need to certify certification info"),
	NOT_FOUND_CERTIFICATION_INFO("JJ013",
		"Certified info not found, need certified certification id or need to login"),
	;

	private final String code;
	private final String message;

	AuthErrorCode(final String code, final String message) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getCode() {
		return code;
	}
}
