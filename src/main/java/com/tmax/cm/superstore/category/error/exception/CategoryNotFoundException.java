package com.tmax.cm.superstore.category.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException() {
        super(ResponseCode.ERROR_CATEGORY_NOT_FOUND);;
    }
}
