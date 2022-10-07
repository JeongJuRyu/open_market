package com.tmax.cm.superstore.seller.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class SellerDeliveryAlreadyDeletedException extends BusinessException {
	public SellerDeliveryAlreadyDeletedException() {
		super(ResponseCode.ERROR_SELLER_ALREADY_DELETED);
	}
}
