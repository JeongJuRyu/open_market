package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class NoMoreReservationException extends BusinessException {
	public NoMoreReservationException(){
		super(ResponseCode.ERROR_NO_MORE_RESERVATION);
	}
}
