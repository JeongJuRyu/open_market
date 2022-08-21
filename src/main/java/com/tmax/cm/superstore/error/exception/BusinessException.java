package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {

    private ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode) {
        super(responseCode.getDescription());

        this.responseCode = responseCode;
    }
}