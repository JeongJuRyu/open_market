package com.tmax.cm.superstore.cart.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class InvalidReservationCartId extends BusinessException {
	public InvalidReservationCartId() {
		super(ResponseCode.ERROR_CART_RESERVATION_ITEM_INVALID);
	}
}
