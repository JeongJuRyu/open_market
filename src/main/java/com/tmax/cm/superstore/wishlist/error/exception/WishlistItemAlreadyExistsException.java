package com.tmax.cm.superstore.wishlist.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class WishlistItemAlreadyExistsException extends BusinessException {
    public WishlistItemAlreadyExistsException() {
        super(ResponseCode.ERROR_WISHLIST_ITEM_ALREADY_EXIST);
    }
}
