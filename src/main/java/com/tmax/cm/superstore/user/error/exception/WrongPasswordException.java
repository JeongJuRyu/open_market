package com.tmax.cm.superstore.user.error.exception;

import org.springframework.security.core.AuthenticationException;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class WrongPasswordException extends BusinessException {
	public WrongPasswordException(){
		super(ResponseCode.ERROR_PASSWORD_NOT_MATCHED);
	}
}
