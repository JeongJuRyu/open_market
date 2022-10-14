package com.tmax.cm.superstore.item.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ItemImageNotFoundException extends BusinessException {
    public ItemImageNotFoundException() {
        super(ResponseCode.ERROR_ITEM_IMAGE_NOT_FOUND);
    }
}
