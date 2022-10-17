package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationExpiredException extends BusinessException {
	public ReservationExpiredException() {
		super(ResponseCode.ERROR_RESERVATION_EXPIRED);
	}
}
