package com.tmax.cm.superstore.shop.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class ShopNotFoundException extends BusinessException {

    public ShopNotFoundException() {
        super(ResponseCode.ERROR_SHOP_NOT_FOUND);
    }
}
