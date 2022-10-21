package com.tmax.cm.superstore.user.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class UserPhoneNumAlreadyExistException extends BusinessException {
	public UserPhoneNumAlreadyExistException(){
		super(ResponseCode.ERROR_PHONE_NUM_ALREADY_EXIST);
	}
}
