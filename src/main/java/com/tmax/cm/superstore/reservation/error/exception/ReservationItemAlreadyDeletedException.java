package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemAlreadyDeletedException extends BusinessException {
	public ReservationItemAlreadyDeletedException() {
		super(ResponseCode.RESERVATION_ITEM_IMAGE_CREATE);
	}
}