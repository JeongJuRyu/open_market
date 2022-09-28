package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemListNotFoundException extends BusinessException {
	public ReservationItemListNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_LIST_NOT_FOUND);
	}
}
