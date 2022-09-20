package com.tmax.cm.superstore.code;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

	// Seller
	SELLER_CREATE("SUCCESS", "SELLER_CREATE", "Create seller", "판매자 계정 생성"),

	// Reservation
	RESERVATION_ITEM_CREATE("SUCCESS", "RESERVATION_ITEM_CREATE", "Create reservationItem", "예약 상품 생성"),
	RESERVATION_ITEM_LIST_FIND("SUCCESS", "RESERVATION_ITEM_LIST_FIND","Find reservationItemList", "예약 상품 리스트 가져오기"),
	RESERVATION_ITEM_OPTION_CREATE("SUCCESS", "RESERVATION_ITEM_OPTION_CREATE", "Create reservationItemOption", "예약 상품 옵션 생성"),
	RESERVATION_ITEM_OPTION_LIST_FIND("SUCCESS", "RESERVATION_ITEM_OPTION_LIST_FIND","Find reservationItemOptionList", "예약 상품 옵션 리스트 가져오기"),
	RESERVATION_ITEM_IMAGE_CREATE("SUCCESS", "RESERVATION_ITEM_IMAGE_CREATE", "Create reservationItemImage", "예약 상품 이미지 등록"),
	RESERVATION_POSSIBLE_DAYS_FIND("SUCCESS","RESERVATION_POSSIBLE_DAYS_FIND","Find reservationPossibleDays","예약 가능 일자 찾기"),
	RESERVATION_POSSIBLE_TIMES_FIND("SUCCESS","RESERVATION_POSSIBLE_TIMES_FIND","Find reservationPossibleTimes","예약 가능 시간 찾기"),
	RESERVATION_MAKE("SUCCESS","RESERVATION_MAKE_SUCCESS","Make reservation","예약 하기"),

	// TempReservation
	RESERVATION_DATE_READ("SUCCESS", "Reservation_DATE_READ", "Read reservable dates", "예약 가능한 날짜 조회"),
	RESERVATION_TIME_READ("SUCCESS", "Reservation_TIME_READ", "Read reservable times", "예약 가능한 시간대 조회"),

	// Wishlist
	WISHLIST_GROUP_CREATE("SUCCESS", "WISHLIST_GROUP_CREATE", "Create wishlist group", "찜 그룹 추가"),
	WISHLIST_GROUP_UPDATE("SUCCESS", "WISHLIST_GROUP_UPDATE", "Update wishlist group", "찜 그룹 수정"),
	WISHLIST_GROUP_ORDER_UPDATE("SUCCESS", "WISHLIST_GROUP_ORDER_UPDATE", "Update wishlist group order", "찜 그룹 순서 변경"),
	WISHLIST_ITEM_GROUP_MOVE_UPDATE("SUCCESS", "WISHLIST_ITEM_GROUP_MOVE_UPDATE", "Update wishlist item group", "찜한 상품 그룹 이동"),
	WISHLIST_GROUP_READ("SUCCESS", "WISHLIST_GROUP_READ", "Read wishlist group", "찜 그룹 조회"),
	WISHLIST_ITEM_READ("SUCCESS", "WISHLIST_ITEM_READ", "Read wishlist item", "찜한 상품 조회"),
	WISHLIST_GROUP_DELETE("SUCCESS", "WISHLIST_GROUP_DELETE", "Delete wishlist group", "찜 그룹 삭제"),
	WISHLIST_ITEM_DELETE("SUCCESS", "WISHLIST_ITEM_DELETE", "Delete wishlist item", "찜 아이템 삭제"),


	// Error - Item
	ERROR_ITEM_NOT_FOUND("ERROR", "EI000", "Item not found", "해당 상품이 저장되어 있지 않음"),
	ERROR_OPTION_GROUP_NOT_FOUND("ERROR", "EI001", "Option group not found", "해당 옵션 그룹이 저장되어 있지 않음"),
	ERROR_OPTION_NOT_FOUND("ERROR", "EI002", "Option not found", "해당 옵션이 저장되어 있지 않음"),
	ERROR_ITEM_IMPOSSIBLE_SEND_TYPE("ERROR", "EI003", "Impossible itemSendType", "해당 상품으로는 불가능한 itemSendType"),

	// Error - Seller
	ERROR_SELLER_ALREADY_DELETED("ERROR", "ES000", "SellerId already deleted", "해당 판매자 계정은 이미 삭제되었음"),
	ERROR_SELLER_NOT_FOUND("ERROR", "ES001", "SellerId not found", "해당 판매자 아이디를 찾을 수 없음"),
	// Error - Reservation
	ERROR_NO_MORE_RESERVATION("ERROR", "ER000", "Cannot make reservation", "예약이 다 차있음"),

	// Error - Cart
	ERROR_CART_ITEM_NOT_FOUND("ERROR", "EC100", "Cart item not found", "해당 카트 상품이 저장되어 있지 않음"),

	// Error - MyPage
	ERROR_REVIEW_NOT_FOUND("ERROR", "EM100", "Review not found", "해당 리뷰가 존재 하지 않음"),
	ERROR_CUSTOMER_INQUIRY_NOT_FOUND("ERROR", "EM101", "Customer inquiry not found", "해당 고객센터 문의가 존재 하지 않음"),
	ERROR_ORDER_INQUIRY_NOT_FOUND("ERROR", "EM102", "Order inquiry not found", "해당 주문 문의가 존재 하지 않음"),

	// Error - User
	ERROR_EMAIL_NOT_FOUND("ERROR", "EU001", "Email not found", "존재 하지 않는 이메일"),
	ERROR_EMAIL_ALREADY_EXIST("ERROR", "EU002", "Already existed email", "이미 존재 하는 이메일"),
	ERROR_EMAIL_NOT_EXPIRED_EXCEPTION("ERROR", "EU003", "authentication email not expired", "인증용 이메일이 만료되지 않음."),
	ERROR_ITEM_INQUIRY_NOT_FOUND("ERROR", "EU004", "Item inquiry not found", "상품 문의가 존재 하지 않음."),
	ERROR_PASSWORD_NOT_MATCHED("ERROR", "EU005", "Password is not matched", "비밀번호 불일치."),

    // Error - Common
    ERROR_COMMON_RUNTIME("ERROR", "EC000", "Runtime Exception", "Runtime Exception 발생한 경우"),
    ERROR_COMMON_CONCURRENCY("ERROR","EC001", "Request occurred simultaneously", "동시 요청으로 인한 오류"),
    ERROR_INVALID_FIELD("ERROR","EC003", "Field is invalid", "@Valid 통과 실패, 유효하지 않은 필드값"),
    ERROR_ENTITY_NOT_FOUND("ERROR","EC004", "Class entity not found", "존재하지 않는 엔티티");

	// Error - Wishlist

    private String status; // API response
    private String code; // API response
    @Setter
    private String message; // API response
    @JsonIgnore
    private String description; // 개발 편의를 위한 description
}
