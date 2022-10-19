package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class DuplicateLoginIdException extends BusinessException {
	public DuplicateLoginIdException() {
		super(ResponseCode.ERROR_SELLER_LOGIN_ID_DUPLICATE);
	}
}
