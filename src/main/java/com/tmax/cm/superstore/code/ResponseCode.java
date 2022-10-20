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
	ITEM_DELETE("SUCCESS","ITEM_DELETE","Delete item","상품 삭제"),
	ITEM_UPDATE("SUCCESS", "ITEM_UPDATE","Update item", "상품 수정"),

	// Cart
	CART_READ("SUCCESS", "CART_READ", "Read cart", "카트 조회"),
	CART_ITEM_CREATE("SUCCESS", "CART_ITEM_CREATE", "Create cart item", "카트 상품 추가"),
	CART_RESERVATION_ITEM_CREATE("SUCCESS", "CART_RESERVATION_ITEM_CREATE", "Create cart reservation item", "카트 예약 추가"),
	CART_ITEM_READ("SUCCESS", "CART_ITEM_READ", "Read cart item", "카트 상품 조회"),
	CART_RESERVATION_ITEM_READ("SUCCESS", "CART_RESERVATION_ITEM_READ", "Create cart reservation item", "카트 예약 조회"),
	CART_ITEM_UPDATE("SUCCESS", "CART_ITEM_UPDATE", "Update cart item", "카트 상품 수정"),
	CART_RESERVATION_ITEM_UPDATE("SUCCESS", "CART_RESERVATION_ITEM_UPDATE", "Update cart reservation item", "카트 예약 수정"),
	CART_ITEMS_DELETE("SUCCESS", "CART_ITEMS_DELETE", "Delete cart items", "카트 상품 대량 삭제"),

	// PurchaseOrder
	PURCHASE_ORDER_CART_CREATE("SUCCESS", "PURCHASE_ORDER_CART_CREATE", "Create purchase order from cart", "장바구니 화면에서 주문서 생성"),
	PURCHASE_ORDER_BUY_NOW_CREATE("SUCCESS", "PURCHASE_ORDER_BUY_NOW_CREATE", "Create purchase order from buy now", "바로 구매 기능으로 주문서 생성"),

	// Order
	ORDER_CREATE("SUCCESS", "ORDER_CREATE", "Create order", "주문 생성"),
	ORDER_VISIT_AND_PICKUP_READ("SUCCESS", "ORDER_VISIT_AND_PICKUP_READ", "Read visit order and pickup order", "방문수령 및 픽업 주문 조회"),
	ORDER_SHIPPING_AND_DELIVERY_READ("SUCCESS", "ORDER_SHIPPING_AND_DELIVERY_READ", "Read shipping order and delivery order", "배송 및 배달 주문 조회"),

	// Seller
	SELLER_CREATE("SUCCESS", "SELLER_CREATE", "Create seller", "판매자 계정 생성"),
	SELLER_LOGIN("SUCCESS", "SELLER_LOGIN", "Login seller", "판매자 계정 로그인"),
	SELLER_DELETE("SUCCESS", "SELLER_DELETE", "Delete seller", "판매자 계정 회원탈퇴"),
	SELLER_INFO_MODIFY("SUCCESS", "SELLER_INFO_MODIFY", "Modify seller Info", "판매자 정보 수정하기"),
	SELLER_LIST_FIND("SUCCESS", "SELLER_LIST_FIND", "Find seller List", "판매자 리스트 가져오기"),
	SELLER_DELIVERY_CREATE("SUCCESS", "SELLER_DELIVERY_CREATE", "Create sellerDelivery", "판매자 배송지 생성"),
	SELLER_DELIVERY_LIST_FIND("SUCCESS", "SELLER_DELIVERY_LIST_FIND", "Find sellerDelivery List", "판매자 배송지 리스트 가져오기"),
	SELLER_REPRESENTATIVE_DELIVERY_FIND("SUCCESS", "SELLER_REPRESENTATIVE_DELIVERY_FIND", "Find representativeDelivery", "대표 배송지 가져오기"),
	SELLER_REPRESENTATIVE_DELIVERY_MODIFY("SUCCESS", "SELLER_REPRESENTATIVE_DELIVERY_MODIFY", "Modify representativeDelivery", "대표 배송지 변경"),
	BUSINESS_MODIFY("SUCCESS", "BUSINESS_MODIFY", "Modify business info", "사업자 정보 수정"),
	BUSINESS_FIND("SUCCESS", "BUSINESS_FIND", "Find business info", "사업자 정보 가져오기"),

	// Reservation
	RESERVATION_ITEM_CREATE("SUCCESS", "RESERVATION_ITEM_CREATE", "Create reservationItem", "예약 상품 생성"),
	RESERVATION_ITEM_MODIFY("SUCCESS", "RESERVATION_ITEM_MODIFY", "Modify reservationItem", "예약 상품 수정"),
	RESERVATION_ITEM_DELETE("SUCCESS", "RESERVATION_ITEM_DELETE", "Delete reservationItem", "예약 상품 삭제"),
	RESERVATION_ITEM_ALL_FIND("SUCCESS", "RESERVATION_ITEM_ALL_FIND","Find All reservationItem", "모든 예약 상품 리스트 가져오기"),
	RESERVATION_ITEM_FIND("SUCCESS", "RESERVATION_ITEM_FIND","Find reservationItem", "예약 상품 가져오기"),
	RESERVATION_ITEM_LIST_FIND("SUCCESS", "RESERVATION_ITEM_LIST_FIND","Find reservationItemList", "예약 상품 리스트 가져오기"),
	RESERVATION_ITEM_OPTION_CREATE("SUCCESS", "RESERVATION_ITEM_OPTION_CREATE", "Create reservationItemOption", "예약 상품 옵션 생성"),
	RESERVATION_ITEM_OPTION_MODIFY("SUCCESS", "RESERVATION_ITEM_OPTION_MODIFY", "Modify reservationItemOption", "예약 상품 옵션 수정"),
	RESERVATION_ITEM_OPTION_DELETE("SUCCESS", "RESERVATION_ITEM_OPTION_DELETE", "Delete reservationItemOption", "예약 상품 옵션 삭제"),
	RESERVATION_ITEM_OPTION_LIST_FIND("SUCCESS", "RESERVATION_ITEM_OPTION_LIST_FIND","Find reservationItemOptionList", "예약 상품 옵션 리스트 가져오기"),
	RESERVATION_ITEM_IMAGE_CREATE("SUCCESS", "RESERVATION_ITEM_IMAGE_CREATE", "Create reservationItemImage", "예약 상품 이미지 등록"),
	RESERVATION_ITEM_IMAGE_DELETE("SUCCESS", "RESERVATION_ITEM_IMAGE_DELETE", "Delete reservationItemImage", "예약 상품 이미지 삭제"),
	RESERVATION_ITEM_IMAGE_FIND("SUCCESS", "RESERVATION_ITEM_IMAGE_FIND", "Find reservationItemImage", "예약 상품 이미지 가져오기"),
	RESERVATION_POSSIBLE_DAYS_FIND("SUCCESS","RESERVATION_POSSIBLE_DAYS_FIND","Find reservationPossibleDays","예약 가능 일자 찾기"),
	RESERVATION_POSSIBLE_TIMES_FIND("SUCCESS","RESERVATION_POSSIBLE_TIMES_FIND","Find reservationPossibleTimes","예약 가능 시간 찾기"),
	RESERVATION_MAKE("SUCCESS","RESERVATION_MAKE_SUCCESS","Make reservation","예약 하기"),
	RESERVATION_LIST_BY_SELLER_FIND("SUCCESS", "RESERVATION_LIST_BY_SELLER_FIND","Find reservationList by Seller", "판매자에게 예약 내역 보여주기"),
	RESERVATION_LIST_BY_USER_FIND("SUCCESS", "RESERVATION_LIST_BY_USER_FIND","Find reservationList by User", "유저에게 예약 내역 보여주기"),

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
	WISHLIST_ITEM_CREATE("SUCCESS", "WISHLIST_ITEM_CREATE", "Create wishlist item", "찜한 상품 생성"),
	WISHLIST_ITEM_CHECK_READ("SUCCESS", "WISHLIST_ITEM_CHECK_READ", "Read and check wishlist item", "찜한 상품 여부 조회"),

	// User
	USER_CREATE("SUCCESS", "USER_CREATE", "Create user", "유저 회원가입"),
	USER_EMAIL_UPDATE("SUCCESS", "USER_EMAIL_UPDATE", "Update user email", "유저 이메일 변경"),
	USER_DELETE("SUCCESS", "USER_DELETE", "Delete user", "유저 회원 탈퇴"),
	USER_INFO_READ("SUCCESS", "USER_INFO_READ", "Read user info", "유저 정보 조회"),
	USER_DELIVERY_READ("SUCCESS", "USER_DELIVERY_READ", "Read user delivery", "유저 배송지 조회"),
	USER_DELIVERY_CREATE("SUCCESS", "USER_DELIVERY_CREATE", "Create user delivery", "유저 배송지 추가"),
	USER_DELIVERY_UPDATE("SUCCESS", "USER_DELIVERY_UPDATE", "Update user delivery", "유저 배송지 변경"),
	USER_DELIVERY_DELETE("SUCCESS", "USER_DELIVERY_DELETE", "Delete user delivery", "유저 배송지 삭제"),
	USER_DEFAULT_DELIVERY_SET("SUCCESS", "USER_DEFAULT_DELIVERY_SET", "Set user default delivery", "유저 기본 배송지 지정"),
	USER_LOGIN("SUCCESS", "USER_LOGIN", "Login user", "유저 로그인"),
	USER_PASSWORD_UPDATE("SUCCESS", "USER_PASSWORD_UPDATE", "update user password", "유저 비밀번호 변경"),

	// My-page
	REVIEW_READ_ALL("SUCCESS", "REVIEW_READ_ALL", "Read all review", "리뷰 전체 조회"),
	REVIEW_READ("SUCCESS", "REVIEW_READ", "Read review", "리뷰 단건 조회"),
	REVIEW_CREATE("SUCCESS", "REVIEW_CREATE", "Create review", "리뷰 작성"),
	REVIEW_UPDATE("SUCCESS", "REVIEW_UPDATE", "Update review", "리뷰 수정"),
	REVIEW_DELETE("SUCCESS", "REVIEW_DELETE", "Delete review", "리뷰 삭제"),
	REVIEW_REPLY_CREATE("SUCCESS", "REVIEW_REPLY_CREATE", "Create review reply", "리뷰 답변 작성"),
	REVIEW_REPLY_UPDATE("SUCCESS", "REVIEW_REPLY_UPDATE", "Update review reply", "리뷰 답변 수정"),
	REVIEW_REPLY_DELETE("SUCCESS", "REVIEW_REPLY_DELETE", "Delete review reply", "리뷰 답변 삭제"),
	ORDER_ITEM_INQUIRY_READ_ALL("SUCCESS", "ORDER_ITEM_INQUIRY_READ_ALL", "Read all order item inquiry", "주문 상품 문의 전체 조회"),
	ORDER_ITEM_INQUIRY_READ("SUCCESS", "ORDER_ITEM_INQUIRY_READ", "Read order item inquiry", "주문 상품 문의 단건 조회"),
	ORDER_ITEM_INQUIRY_CREATE("SUCCESS", "ORDER_ITEM_INQUIRY_CREATE", "Create order item inquiry", "주문 상품 문의 작성"),
	ORDER_ITEM_INQUIRY_UPDATE("SUCCESS", "ORDER_ITEM_INQUIRY_UPDATE", "Update order item inquiry", "주문 상품 문의 수정"),
	ORDER_ITEM_INQUIRY_DELETE("SUCCESS", "ORDER_ITEM_INQUIRY_DELETE", "Delete order item inquiry", "주문 상품 문의 삭제"),
	ORDER_ITEM_INQUIRY_REPLY_CREATE("SUCCESS", "ORDER_ITEM_INQUIRY_REPLY_CREATE", "Create order item inquiry reply", "주문 상품 문의 답변 작성"),
	ORDER_ITEM_INQUIRY_REPLY_UPDATE("SUCCESS", "ORDER_ITEM_INQUIRY_REPLY_UPDATE", "Update order item inquiry reply", "주문 상품 문의 답변 수정"),
	ORDER_ITEM_INQUIRY_REPLY_DELETE("SUCCESS", "ORDER_ITEM_INQUIRY_REPLY_DELETE", "Delete order item inquiry reply", "주문 상품 문의 답변 삭제"),

	// Error - category
	ERROR_CATEGORY_NOT_FOUND("ERROR", "ECT000", "Category not found", "해당 카테고리가 존재하지 않습니다."),

	// Error - shop
	ERROR_SHOP_NOT_FOUND("ERROR", "ES000", "Shop not found", "해당 가게는 존재하지 않습니다."),

	// Ship
	SHIP_CREATE("SUCCESS", "SHIP_CREATE", "Create ship", "배송 목록 생성"),
	SHIP_READ("SUCCESS", "SHIP_READ", "Read ship", "배송 목록 조회"),
	SHIP_STATUS_UPDATE("SUCCESS", "SHIP_STATUS_UPDATE", "Update ship's status", "배송 상태 변경"),
	SHIP_UPDATE("SUCCESS", "SHIP_UPDATE", "Update ship", "배송 목록 수정"),

	// Error - Item
	ERROR_ITEM_NOT_FOUND("ERROR", "EI000", "Item not found", "해당 상품이 저장되어 있지 않음"),
	ERROR_OPTION_GROUP_NOT_FOUND("ERROR", "EI001", "Option group not found", "해당 옵션 그룹이 저장되어 있지 않음"),
	ERROR_OPTION_NOT_FOUND("ERROR", "EI002", "Option not found", "해당 옵션이 저장되어 있지 않음"),
	ERROR_ITEM_IMPOSSIBLE_SEND_TYPE("ERROR", "EI003", "Impossible itemSendType", "해당 상품으로는 불가능한 itemSendType"),

	// Error - ItemImage
	ERROR_ITEM_IMAGE_NOT_FOUND("ERROR", "EIM000", "Item Image not found","해당 상품의 이미지을 찾지 못함"),

	// Error - Seller
	ERROR_SELLER_ALREADY_DELETED("ERROR", "ES000", "SellerId already deleted", "해당 판매자 계정은 이미 삭제되었음"),
	ERROR_SELLER_NOT_FOUND("ERROR", "ES001", "SellerId not found", "해당 판매자 아이디를 찾을 수 없음"),
	ERROR_SELLER_LIST_NOT_FOUND("ERROR", "ES002", "SellerList not found", "등록된 판매자가 없음"),
	ERROR_SELLER_DELIVERY_ALREADY_DELETED("ERROR", "ES003", "SellerDeliveryId already deleted", "해당 배송지 정보는 이미 삭제되었음"),
	ERROR_SELLER_DELIVERY_NOT_FOUND("ERROR", "ES004", "SellerDeliveryId not found", "해당 판매자 배송지 아이디를 찾을 수 없음"),
	ERROR_SELLER_DELIVERY_LIST_NOT_FOUND("ERROR", "ES005", "SelleDeliveryList not found", "해당 판매자의 배송지 리스트가 없음"),
	ERROR_SELLER_PASSWORD_INVALID("ERROR", "ES006", "SellerPassword invalid", "해당 계정의 비밀번호가 일치하지 않습니다."),
	ERROR_SELLER_LOGIN_ID_DUPLICATE("ERROR", "ES007", "SellerLoginId is duplicated", "해당 아이디는 이미 사용중입니다."),
	ERROR_BUSINESS_NOT_FOUND("ERROR", "EB000", "Business not found", "해당 판매자의 사업자 정보가 없음"),

	// Error - Reservation
	ERROR_NO_MORE_RESERVATION("ERROR", "ER000", "Cannot make reservation", "예약이 다 차있음"),
	ERROR_RESERVATION_NOT_FOUND("ERROR","ER001","ReservationId not found", "해당 예약을 찾을 수 없음"),
	ERROR_RESERVATION_EXPIRED("ERROR","ER002","ReservationTime expired", "해당 예약은 이미 시간이 지난 예약입니다."),
	ERROR_RESERVATION_MODIFY_MUST_BE_SAME_SELLER("ERROR","ER003","Reservation Modify must be same seller", "예약 상품 변경은 같은 판매자의 상품만 가능합니다."),
	ERROR_RESERVATION_MODIFY_MUST_BE_SAME_USER("ERROR","ER004","Reservation Modify must be same user", "예약 상품 변경은 같은 유저의 예약만 가능합니다."),
	ERROR_RESERVATION_ITEM_ALREADY_DELETED("ERROR","ER100","ReservationItemId already deleted", "해당 예약 상품은 이미 삭제되었음"),
	ERROR_RESERVATION_ITEM_NOT_FOUND("ERROR","ER101","ReservationItemId not found", "해당 예약 상품을 찾을 수 없음"),
	ERROR_RESERVATION_ITEM_LIST_NOT_FOUND("ERROR","ER102","ReservationItemList not found", "해당 판매자의 예약 상품 리스트를 찾을 수 없음"),
	ERROR_RESERVATION_ITEM_IMAGE_NOT_FOUND("ERROR","ER103","ReservationItemImageId not found", "해당 예약 상품 이미지를 찾을 수 없음"),
	ERROR_RESERVATION_ITEM_OPTION_ALREADY_DELETED("ERROR","ER200","ReservationItemOptionId already deleted", "해당 예약 상품 옵션은 이미 삭제되었음"),
	ERROR_RESERVATION_ITEM_OPTION_NOT_FOUND("ERROR","ER201","ReservationItemOptionId not found", "해당 예약 상품 옵션을 찾을 수 없음"),
	ERROR_RESERVATION_ITEM_OPTION_LIST_NOT_FOUND("ERROR","ER202","ReservationItemOptionList not found", "해당 상품의 옵션 리스트를 찾을 수 없음"),

	// Error - Cart
	ERROR_CART_ITEM_NOT_FOUND("ERROR", "EC100", "Cart item not found", "해당 카트 상품이 저장되어 있지 않음"),

	// Error - MyPage
	ERROR_REVIEW_NOT_FOUND("ERROR", "EM100", "Review not found", "해당 리뷰가 존재 하지 않음"),
	ERROR_REVIEW_REPLY_NOT_FOUND("ERROR", "EM101", "Review reply not found", "해당 리뷰 답변이 존재 하지 않음"),
	ERROR_CUSTOMER_INQUIRY_NOT_FOUND("ERROR", "EM102", "Customer inquiry not found", "해당 고객센터 문의가 존재 하지 않음"),
	ERROR_ORDER_INQUIRY_NOT_FOUND("ERROR", "EM103", "Order inquiry not found", "해당 주문 문의가 존재 하지 않음"),
	ERROR_ITEM_INQUIRY_NOT_FOUND("ERROR", "EM104", "Item inquiry not found", "상품 문의가 존재 하지 않음."),
	ERROR_ITEM_INQUIRY_REPLY_NOT_FOUND("ERROR", "EM105", "Item inquiry reply not found", "상품 문의 답변이 존재 하지 않음."),
	ERROR_CUSTOMER_INQUIRY_REPLY_NOT_FOUND("ERROR", "EM106", "Customer inquiry reply not found", "해당 고객센터 문의 답변이 존재 하지 않음"),

	// Error - User
	ERROR_EMAIL_NOT_FOUND("ERROR", "EU001", "Email not found", "해당 이메일이 존재하지 않음"),
	ERROR_EMAIL_ALREADY_EXIST("ERROR", "EU002", "Already existed email", "이미 존재 하는 이메일"),
	ERROR_EMAIL_NOT_EXPIRED_EXCEPTION("ERROR", "EU003", "authentication email not expired", "인증용 이메일이 만료되지 않음."),
	ERROR_PASSWORD_NOT_MATCHED("ERROR", "EU004", "Password is not matched", "해당 비밀번호가 일치하지 않음."),
	ERROR_DELIVERY_ADDRESS_NOT_FOUND("ERROR", "EU005", "Delivery address not found", "해당 배송지 정보가 존재하지 않음"),

    // Error - Common
    ERROR_COMMON_RUNTIME("ERROR", "EC000", "Runtime Exception", "Runtime Exception 발생한 경우"),
    ERROR_COMMON_CONCURRENCY("ERROR","EC001", "Request occurred simultaneously", "동시 요청으로 인한 오류"),
    ERROR_INVALID_FIELD("ERROR","EC003", "Field is invalid", "@Valid 통과 실패, 유효하지 않은 필드값"),
    ERROR_ENTITY_NOT_FOUND("ERROR","EC004", "Class entity not found", "존재하지 않는 엔티티"),
	ERROR_INTERNAL_SERVER_ERROR("ERROR", "EC005", "Internal server error", "내부 서버 오류"),
	ERROR_UNAUTHENTICATED("ERROR", "EC006", "Unauthenticated", "spring security 인증 실패"),
	ERROR_UNAUTHORIZED("ERROR", "EC007", "Unauthorized", "spring security 인가 실패"),

	// Error - Wishlist
	ERROR_WISHLIST_ITEM_ALREADY_EXIST("ERROR", "EW000", "Wishlist Item already exists", "이미 존재하는 엔티티");

    private String status; // API response
    private String code; // API response
    @Setter
    private String message; // API response
    @JsonIgnore
    private String description; // 개발 편의를 위한 description
}
