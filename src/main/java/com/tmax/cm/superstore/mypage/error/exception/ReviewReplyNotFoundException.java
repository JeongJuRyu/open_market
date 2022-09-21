package com.tmax.cm.superstore.mypage.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReviewReplyNotFoundException extends BusinessException {
	public ReviewReplyNotFoundException() {
		super(ResponseCode.ERROR_REVIEW_REPLY_NOT_FOUND);
	}
}
