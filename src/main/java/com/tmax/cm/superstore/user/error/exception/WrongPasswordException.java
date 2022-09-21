package com.tmax.cm.superstore.user.error.exception;

import org.springframework.security.core.AuthenticationException;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class WrongPasswordException extends AuthenticationException {
	public WrongPasswordException(){
		super("wrong password");
	}
}
