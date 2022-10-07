package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class SellerListNotFoundException extends BusinessException {
	public SellerListNotFoundException() {
		super(ResponseCode.ERROR_SELLER_LIST_NOT_FOUND);
	}
}
