package com.tmax.cm.superstore.user.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class EmailNotFoundException extends BusinessException {
	public EmailNotFoundException(){
		super(ResponseCode.ERROR_EMAIL_NOT_FOUND);
	}
}
