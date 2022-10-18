package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class InvalidSellerPasswordException extends BusinessException {
	public InvalidSellerPasswordException() {
		super(ResponseCode.ERROR_SELLER_PASSWORD_INVALID);
	}
}
