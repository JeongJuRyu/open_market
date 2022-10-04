package com.tmax.cm.superstore.user.error.exception;


import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class DeliveryAddressNotFoundException extends BusinessException {
	public DeliveryAddressNotFoundException(){
		super(ResponseCode.ERROR_DELIVERY_ADDRESS_NOT_FOUND);
	}
}
