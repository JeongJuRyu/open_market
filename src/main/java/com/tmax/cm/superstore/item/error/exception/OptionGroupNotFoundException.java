package com.tmax.cm.superstore.item.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class OptionGroupNotFoundException extends BusinessException {
    public OptionGroupNotFoundException() {
        super(ResponseCode.ERROR_OPTION_GROUP_NOT_FOUND);
    }
}
