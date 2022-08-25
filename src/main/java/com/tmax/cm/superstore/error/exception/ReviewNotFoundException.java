package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class ReviewNotFoundException extends BusinessException{
	public ReviewNotFoundException() { super(ResponseCode.ERROR_REVIEW_NOT_FOUND);}
}
