package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class OptionGroupNotFoundException extends BusinessException {
    public OptionGroupNotFoundException() {
        super(ResponseCode.ERROR_OPTION_GROUP_NOT_FOUND);
    }
}
