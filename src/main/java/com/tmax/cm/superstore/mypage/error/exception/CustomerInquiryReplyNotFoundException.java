package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class CustomerInquiryReplyNotFoundException extends BusinessException {
	public CustomerInquiryReplyNotFoundException(){
		super(ResponseCode.ERROR_CUSTOMER_INQUIRY_REPLY_NOT_FOUND);
	}
}
