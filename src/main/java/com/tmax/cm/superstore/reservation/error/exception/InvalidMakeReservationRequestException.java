package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class InvalidMakeReservationRequestException extends BusinessException {
	public InvalidMakeReservationRequestException() {
		super(ResponseCode.ERROR_MAKE_RESERVATION_REQUEST_INVALID);
	}
}
