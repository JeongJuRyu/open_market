package com.tmax.cm.superstore.reservation.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ModifyReservationMustBeSameSellerException extends BusinessException {
	public ModifyReservationMustBeSameSellerException() {
		super(ResponseCode.ERROR_RESERVATION_MODIFY_MUST_BE_SAME_SELLER);
	}
}
