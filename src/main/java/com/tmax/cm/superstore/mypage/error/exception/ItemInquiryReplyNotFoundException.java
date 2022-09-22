package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ItemInquiryReplyNotFoundException extends BusinessException {
	public ItemInquiryReplyNotFoundException(){
		super(ResponseCode.ERROR_ORDER_INQUIRY_NOT_FOUND);
	}
}
