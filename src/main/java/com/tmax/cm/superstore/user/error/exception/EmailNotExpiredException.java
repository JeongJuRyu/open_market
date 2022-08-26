package com.tmax.cm.superstore.user.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class EmailNotExpiredException extends BusinessException {

	public EmailNotExpiredException() {
		super(ResponseCode.ERROR_EMAIL_NOT_EXPIRED_EXCEPTION);
	}
}
