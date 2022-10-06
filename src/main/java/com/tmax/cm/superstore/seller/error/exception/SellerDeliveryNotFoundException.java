package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class SellerDeliveryNotFoundException extends BusinessException {
	public SellerDeliveryNotFoundException() {
		super(ResponseCode.ERROR_SELLER_DELIVERY_NOT_FOUND);
	}
}
