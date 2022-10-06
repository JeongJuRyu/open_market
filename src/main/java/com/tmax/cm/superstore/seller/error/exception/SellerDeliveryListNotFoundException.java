package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class SellerDeliveryListNotFoundException extends BusinessException {
	public SellerDeliveryListNotFoundException() {
		super(ResponseCode.ERROR_SELLER_DELIVERY_LIST_NOT_FOUND);
	}
}
