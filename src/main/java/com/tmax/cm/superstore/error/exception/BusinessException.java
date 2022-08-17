package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ResponseCode errorCode;

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.getDescription());

        this.errorCode = errorCode;
    }
}