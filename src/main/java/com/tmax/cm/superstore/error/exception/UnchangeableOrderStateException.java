package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class UnchangeableOrderStateException extends BusinessException {
    public UnchangeableOrderStateException() {
        super(ResponseCode.ERROR_ORDER_STATE_UNCHANGEABLE);
    }
}
