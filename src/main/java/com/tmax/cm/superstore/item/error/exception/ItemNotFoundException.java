package com.tmax.cm.superstore.item.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ItemNotFoundException extends BusinessException {
    public ItemNotFoundException() {
        super(ResponseCode.ERROR_ITEM_NOT_FOUND);
    }
}
