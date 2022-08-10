package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());

        this.errorCode = errorCode;
    }
}