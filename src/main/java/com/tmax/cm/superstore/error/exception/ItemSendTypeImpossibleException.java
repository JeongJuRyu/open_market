package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class ItemSendTypeImpossibleException extends BusinessException{
    public ItemSendTypeImpossibleException() {
        super(ResponseCode.ERROR_ITEM_IMPOSSIBLE_SEND_TYPE);
    }
}
