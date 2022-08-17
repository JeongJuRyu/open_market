package com.tmax.cm.superstore.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode implements Code {

    // Item
    ITEM_CREATE("SUCCESS","ITEM_CREATE", "Create item", "아이템 생성"),

    // Error - Common
    ERROR_COMMON_RUNTIME("ERROR", "EC000", "Runtime Exception", "Runtime Exception 발생한 경우"),
    ERROR_COMMON_CONCURRENCY("ERROR","EC001", "Request occured simultaneously", "동시 요청으로 인한 오류");

    private String status; // API response
    private String code; // API response
    private String message; // API response
    private String description; // 개발 편의를 위한 description
}
