package com.tmax.cm.superstore.error.lib;

public enum UserErrorCode {
	USER_ID_NOT_FOUND("U1001", "UserId Not Found"),
	USER_ID_ALREADY_DELETED("U1002", "UserId Already Deleted"),
	USER_ALREADY_EXIST("U1002", "User already exist"),
	PHONE_ALREADY_EXIST("U1003", "Phone Number already exist"),

	PASS_VALIDATION_ERROR("GJ1000", "Password validation error"),
	WRONG_PASSWORD_FORMAT("GJ1000", "Wrong password format"),
	PASSWORD_NOT_CHANGED("GJ1000", "Password is same as the old password");

	private final String code;
	private final String message;

	UserErrorCode(final String code, final String message) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
}
