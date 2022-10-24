package com.tmax.cm.superstore.cart.dto.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.code.SendType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class GetReservationCartItemDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationCartId;
		private UUID reservationCartItemId;
		private UUID reservationItemId;
		private String reservationItemName;
		private String reservationItemPrice;
		private UUID reservationItemOptionId;
		private String reservationItemOptionName;
		private String reservationItemOptionPrice;
		private SendType sendType;
		private String userName;
		private String userPhoneNum;
		private String userEmail;
		private CartReservationInfoRequest cartReservationInfo;

		public static ResponseBuilder builder(
			ReservationCartItem reservationCartItem) {
			return ResponseBuilder()
				.reservationCartId(reservationCartItem.getReservationCartId().getId())
				.reservationCartItemId(reservationCartItem.getId())
				.reservationItemId(reservationCartItem.getReservationItem().getReservationItemId())
				.reservationItemName(reservationCartItem.getReservationItem().getReservationItemName())
				.reservationItemPrice(reservationCartItem.getReservationItem().getReservationItemPrice())
				.reservationItemOptionId(reservationCartItem.getReservationItemOption().getOptionId())
				.reservationItemOptionName(reservationCartItem.getReservationItemOption().getOptionName())
				.reservationItemOptionPrice(reservationCartItem.getReservationItemOption().getOptionPrice())
				.sendType(reservationCartItem.getSendType())
				.userName(reservationCartItem.getReservationCartId().getUser().getName())
				.userPhoneNum(reservationCartItem.getReservationCartId().getUser().getPhoneNum())
				.userEmail(reservationCartItem.getReservationCartId().getUser().getEmail())
				.cartReservationInfo(
					CartReservationInfoRequest.builder(reservationCartItem.getCartReservationInfo()).build());
		}
	}

	@Getter
	@Builder(builderMethodName = "CartReservationInfoRequestBuilder")
	public static class CartReservationInfoRequest {
		private LocalDate reservationDay;
		private LocalDateTime reservationTime;
		private Integer numberOfPeople;
		private String customerRequest;

		public static CartReservationInfoRequestBuilder builder(
			CartReservationInfo cartReservationInfo) {
			return CartReservationInfoRequestBuilder()
				.reservationDay(cartReservationInfo.getReservationDay())
				.reservationTime(cartReservationInfo.getReservationTime())
				.numberOfPeople(cartReservationInfo.getNumberOfPeople())
				.customerRequest(cartReservationInfo.getCustomerRequest());
		}
	}
}
