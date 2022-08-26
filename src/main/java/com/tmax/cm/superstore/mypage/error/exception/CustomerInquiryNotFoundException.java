package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class CustomerInquiryNotFoundException extends BusinessException {
	@Override
	public ResponseCode getResponseCode() {
		return super.getResponseCode();
	}

	public CustomerInquiryNotFoundException() {
		super(ResponseCode.ERROR_CUSTOMER_INQUIRY_NOT_FOUND);
	}
}
