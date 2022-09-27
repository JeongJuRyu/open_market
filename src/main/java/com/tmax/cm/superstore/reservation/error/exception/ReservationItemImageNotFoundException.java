package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemImageNotFoundException extends BusinessException {
	public ReservationItemImageNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_IMAGE_NOT_FOUND);
	}
}
