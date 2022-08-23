package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class CartItemNotFoundException extends BusinessException {
    public CartItemNotFoundException() {
        super(ResponseCode.ERROR_CART_ITEM_NOT_FOUND);
    }
}
