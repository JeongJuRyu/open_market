package com.tmax.cm.superstore.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements Code {
    
    COMMON_RUNTIME_ERROR(HttpStatus.BAD_REQUEST, "C000", "Runtime Exception","Runtime Exception 발생한 경우"),
    COMMON_CONCURRENCY_ERROR(HttpStatus.BAD_REQUEST, "C001", "Request occured simultaneously","동시 요청으로 인한 오류");

    private HttpStatus httpStatus;
    private String code;
    private String message; // 에러 발생 시 response message
    private String description; // 개발 편의를 위한 description
}
