package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {
	public ReservationNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_NOT_FOUND);
	}
}
