package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ReservationItemOptionListNotFoundException extends BusinessException {
	public ReservationItemOptionListNotFoundException() {
		super(ResponseCode.ERROR_RESERVATION_ITEM_OPTION_LIST_NOT_FOUND);
	}
}
