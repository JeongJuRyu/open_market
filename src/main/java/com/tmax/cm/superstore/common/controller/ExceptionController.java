package com.tmax.cm.superstore.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tmax.cm.superstore.code.ErrorCode;
import com.tmax.cm.superstore.error.ErrorResponse;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> responseRuntimeException(RuntimeException runtimeException) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.COMMON_RUNTIME_ERROR);
        errorResponse.setMessage(runtimeException.getMessage());

        return ResponseEntity.status(ErrorCode.COMMON_RUNTIME_ERROR.getHttpStatus())
                .body(errorResponse);
    }
}
