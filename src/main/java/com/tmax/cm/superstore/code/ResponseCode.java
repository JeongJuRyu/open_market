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
	ITEM_CREATE("SUCCESS", "ITEM_CREATE", "Create item", "상품 생성"),
	ITEM_READ("SUCCESS", "ITEM_READ", "Read item", "특정 상품 조회"),
	ITEM_READ_ALL("SUCCESS", "ITEM_READ_ALL", "Read item all", "모든 상품 조회"),

	// Cart
	CART_READ("SUCCESS", "CART_READ", "Read cart", "카트 조회"),
	CART_ITEM_CREATE("SUCCESS", "CART_ITEM_CREATE", "Create cart item", "카트 상품 추가"),
	CART_ITEM_READ("SUCCESS", "CART_ITEM_READ", "Read cart item", "카트 상품 조회"),
	CART_ITEM_UPDATE("SUCCESS", "CART_ITEM_UPDATE", "Update cart items", "카트 상품 수정"),
	CART_ITEMS_DELETE("SUCCESS", "CART_ITEMS_DELETE", "Delete cart items", "카트 상품 대량 삭제"),

	// Seller
	SELLER_CREATE("SUCCESS", "SELLER_CREATE", "Create seller", "판매자 계정 생성"),

	// Reservation
	RESERVATION_ITEM_CREATE("SUCCESS", "RESERVATION_ITEM_CREATE", "Create reservationItem", "예약 상품 생성"),
	RESERVATION_ITEM_IMAGE_CREATE("SUCCESS", "RESERVATION_ITEM_IMAGE_CREATE", "Create reservationItemImage",
		"예약 상품 이미지 등록"),

	// Error - Item
	ERROR_ITEM_NOT_FOUND("ERROR", "EI000", "Item not found", "해당 상품이 저장되어 있지 않음"),
	ERROR_OPTION_GROUP_NOT_FOUND("ERROR", "EI001", "Option group not found", "해당 옵션 그룹이 저장되어 있지 않음"),
	ERROR_OPTION_NOT_FOUND("ERROR", "EI002", "Option not found", "해당 옵션이 저장되어 있지 않음"),

	// Error - Cart
	ERROR_CART_ITEM_NOT_FOUND("ERROR", "EC100", "Cart item not found", "해당 카트 상품이 저장되어 있지 않음"),

	// Error - Seller
	ERROR_SELLER_NOT_FOUND("ERROR", "ES000", "Seller not found", "해당 판매자 계정이 존재하지 않음"),
	ERROR_SELLER_ALREADY_DELETED("ERROR", "ES001", "Seller already deleted", "해당 판매자 계정은 이미 삭제됨"),

	// Error - Common
	ERROR_COMMON_RUNTIME("ERROR", "EC000", "Runtime Exception", "Runtime Exception 발생한 경우"),
	ERROR_COMMON_CONCURRENCY("ERROR", "EC001", "Request occured simultaneously", "동시 요청으로 인한 오류"),

	// User
	ERROR_EMAIL_NOT_FOUND("ERROR", "EU001", "Email not found", "존재하지 않는 이메일"),
	ERROR_EMAIL_ALREADY_EXIST("ERROR", "EU002", "Already existed email", "이미 존재하는 이메일");

	private String status; // API response
	private String code; // API response
	private String message; // API response
	@JsonIgnore
	private String description; // 개발 편의를 위한 description
}
