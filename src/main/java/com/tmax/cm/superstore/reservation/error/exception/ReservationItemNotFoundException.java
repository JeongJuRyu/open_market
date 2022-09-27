package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemNotFoundException extends BusinessException {
	public ReservationItemNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_NOT_FOUND);
	}
}
