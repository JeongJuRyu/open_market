
package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class OptionNotFoundException extends BusinessException {
    public OptionNotFoundException() {
        super(ResponseCode.ERROR_OPTION_NOT_FOUND);
    }
}