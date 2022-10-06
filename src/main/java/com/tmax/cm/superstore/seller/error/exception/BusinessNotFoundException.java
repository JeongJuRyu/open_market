package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class BusinessNotFoundException extends BusinessException {

	public BusinessNotFoundException() {
		super(ResponseCode.ERROR_BUSINESS_NOT_FOUND);
	}
}
