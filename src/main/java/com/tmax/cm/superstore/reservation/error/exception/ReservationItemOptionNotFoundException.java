package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemOptionNotFoundException extends BusinessException {
	public ReservationItemOptionNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_OPTION_NOT_FOUND);
	}
}
