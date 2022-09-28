package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemOptionAlreadyDeletedException extends BusinessException {
	public ReservationItemOptionAlreadyDeletedException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_OPTION_ALREADY_DELETED);
	}
}
