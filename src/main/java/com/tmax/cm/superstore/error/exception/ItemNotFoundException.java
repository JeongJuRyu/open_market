package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException() {
        super(ResponseCode.ERROR_ITEM_NOT_FOUND);
    }
}
