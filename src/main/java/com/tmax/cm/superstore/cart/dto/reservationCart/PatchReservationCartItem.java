package com.tmax.cm.superstore.cart.dto.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.code.SendType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PatchReservationCartItem {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private UUID reservationItemId;
		private UUID reservationItemOptionId;
		private SendType sendType;
		private CartReservationInfoRequest cartReservationInfoRequest;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID ReservationCartId;
		private String reservationItemName;
		private String reservationItemOptionName;
		private SendType sendType;
		private CartReservationInfoRequest cartReservationInfo;

		public static ResponseBuilder builder(
			ReservationCartItem reservationCartItem) {
			return ResponseBuilder()
				.ReservationCartId(reservationCartItem.getReservationCartId().getId())
				.reservationItemName(reservationCartItem.getReservationItem().getReservationItemName())
				.reservationItemOptionName(reservationCartItem.getReservationItemOption().getOptionName())
				.sendType(reservationCartItem.getSendType())
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

		public static CartReservationInfoRequest.CartReservationInfoRequestBuilder builder(
			CartReservationInfo cartReservationInfo) {
			return CartReservationInfoRequestBuilder()
				.reservationDay(cartReservationInfo.getReservationDay())
				.reservationTime(cartReservationInfo.getReservationTime())
				.numberOfPeople(cartReservationInfo.getNumberOfPeople())
				.customerRequest(cartReservationInfo.getCustomerRequest());
		}
	}
}
