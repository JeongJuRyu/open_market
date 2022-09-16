package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ItemInquiryNotFoundException extends BusinessException {
	public ItemInquiryNotFoundException() {
		super(ResponseCode.ERROR_ITEM_INQUIRY_NOT_FOUND);
	}
}
