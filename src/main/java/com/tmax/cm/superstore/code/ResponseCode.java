package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCode implements Code {

    // Item
    ITEM_CREATE("SUCCESS","ITEM_CREATE", "Create item", "상품 생성"),
    ITEM_READ("SUCCESS","ITEM_READ", "Read item", "특정 상품 조회"),
    ITEM_READ_ALL("SUCCESS","ITEM_READ_ALL", "Read item all", "모든 상품 조회"),

    // Cart
    CART_READ("SUCCESS", "CART_READ", "Read cart", "카트 조회"),
    CART_ITEM_CREATE("SUCCESS", "CART_ITEM_CREATE", "Create cart item", "카트 상품 추가"),
    CART_RESERVATION_ITEM_CREATE("SUCCESS", "CART_RESERVATION_ITEM_CREATE", "Create cart reservation item", "카트 예약 추가"),
    CART_ITEM_READ("SUCCESS", "CART_ITEM_READ", "Read cart item", "카트 상품 조회"),
    CART_RESERVATION_ITEM_READ("SUCCESS", "CART_RESERVATION_ITEM_READ", "Create cart reservation item", "카트 예약 조회"),
    CART_ITEM_UPDATE("SUCCESS", "CART_ITEM_UPDATE", "Update cart item", "카트 상품 수정"),
    CART_RESERVATION_ITEM_UPDATE("SUCCESS", "CART_RESERVATION_ITEM_UPDATE", "Update cart reservation item", "카트 예약 수정"),
    CART_ITEMS_DELETE("SUCCESS", "CART_ITEMS_DELETE", "Delete cart items", "카트 상품 대량 삭제"),

    // Reservation
    RESERVATION_DATE_READ("SUCCESS", "Reservation_DATE_READ", "Read reservable dates", "예약 가능한 날짜 조회"),
    RESERVATION_TIME_READ("SUCCESS", "Reservation_TIME_READ", "Read reservable times", "예약 가능한 시간대 조회"),

    // Error - Item
    ERROR_ITEM_NOT_FOUND("ERROR", "EI000", "Item not found", "해당 상품이 저장되어 있지 않음"),
    ERROR_OPTION_GROUP_NOT_FOUND("ERROR", "EI001", "Option group not found", "해당 옵션 그룹이 저장되어 있지 않음"),
    ERROR_OPTION_NOT_FOUND("ERROR", "EI002", "Option not found", "해당 옵션이 저장되어 있지 않음"),
    ERROR_ITEM_IMPOSSIBLE_SEND_TYPE("ERROR", "EI003", "Impossible itemSendType", "해당 상품으로는 불가능한 itemSendType"),
    
    // Error - Cart
    ERROR_CART_ITEM_NOT_FOUND("ERROR", "EC100", "Cart item not found", "해당 카트 상품이 저장되어 있지 않음"),

    // Error - MyPage
    ERROR_REVIEW_NOT_FOUND("ERROR", "EM100", "Review not found", "해당 리뷰가 존재하지 않음"),
    ERROR_CUSTOMER_INQUIRY_NOT_FOUND("ERROR", "EM101", "Customer inquiry not found", "해당 고객센터 문의가 존재하지 않음"),

    // Error - User
    ERROR_EMAIL_NOT_FOUND("ERROR", "EU001", "Email not found", "존재하지 않는 이메일"),
    ERROR_EMAIL_ALREADY_EXIST("ERROR", "EU002", "Already existed email", "이미 존재하는 이메일"),
    ERROR_EMAIL_NOT_EXPIRED_EXCEPTION("ERROR", "EU003", "authentication email not expired", "인증용 이메일이 만료되지 않았습니다."),
    // Error - Common
    ERROR_COMMON_RUNTIME("ERROR", "EC000", "Runtime Exception", "Runtime Exception 발생한 경우"),
    ERROR_COMMON_CONCURRENCY("ERROR","EC001", "Request occured simultaneously", "동시 요청으로 인한 오류");


    private String status; // API response
    private String code; // API response
    private String message; // API response
    @JsonIgnore
    private String description; // 개발 편의를 위한 description
}
