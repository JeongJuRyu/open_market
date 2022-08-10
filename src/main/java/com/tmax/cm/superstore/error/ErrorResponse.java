package com.tmax.cm.superstore.error;

import org.springframework.http.ResponseEntity;

import com.tmax.cm.superstore.code.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {
    private String code;

    @Setter
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    static ResponseEntity<ErrorResponse> response(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new ErrorResponse(errorCode));
    }
}
