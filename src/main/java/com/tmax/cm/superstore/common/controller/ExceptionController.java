package com.tmax.cm.superstore.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.error.exception.BusinessException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDto<String> responseRuntimeException(RuntimeException runtimeException) {

        return new ResponseDto<String>(ResponseCode.ERROR_COMMON_RUNTIME, runtimeException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseDto<Void> responseRuntimeException(BusinessException businessException) {

        return new ResponseDto<Void>(businessException.getResponseCode(), null);
    }
}
