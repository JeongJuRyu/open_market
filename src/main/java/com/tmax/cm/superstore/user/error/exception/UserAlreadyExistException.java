package com.tmax.cm.superstore.user.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class UserAlreadyExistException extends BusinessException {
	public UserAlreadyExistException() {
		super(ResponseCode.ERROR_EMAIL_NOT_FOUND);
	}
}
