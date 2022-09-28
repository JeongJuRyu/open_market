package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class ItemImageNotFoundException extends BusinessException{
    public ItemImageNotFoundException() {
        super(ResponseCode.ERROR_ITEM_IMAGE_NOT_FOUND);
    }
}
