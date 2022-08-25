package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class SellerNotFoundException extends BusinessException {
	public SellerNotFoundException(){
			super(ResponseCode.ERROR_SELLER_NOT_FOUND);
	}
}
