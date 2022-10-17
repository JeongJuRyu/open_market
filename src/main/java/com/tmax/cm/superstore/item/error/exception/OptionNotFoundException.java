
package com.tmax.cm.superstore.item.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class OptionNotFoundException extends BusinessException {
    public OptionNotFoundException() {
        super(ResponseCode.ERROR_OPTION_NOT_FOUND);
    }
}