package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReviewNotFoundException extends BusinessException {
	public ReviewNotFoundException() { super(ResponseCode.ERROR_REVIEW_NOT_FOUND);}
}
