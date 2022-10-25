package com.tmax.cm.superstore.cart.dto.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.code.SendType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GetReservationCartItemListDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {

		private List<ReservationCartList> reservationCartList;

		public static ResponseBuilder builder(List<ReservationCartList> reservationCartList) {
			return ResponseBuilder()
				.reservationCartList(reservationCartList);
		}

		@Getter
		@Builder(builderMethodName = "ReservationCartListBuilder")
		public static class ReservationCartList {
			private UUID reservationCartId;
			private UUID reservationCartItemId;
			private String reservationItemName;
			private String reservationItemOptionName;
			private SendType sendType;
			private CartReservationInfoRequest cartReservationInfo;

			public static ReservationCartListBuilder builder(
				ReservationCartItem reservationCartItem) {
				return ReservationCartListBuilder()
					.reservationCartId(reservationCartItem.getReservationCartId().getId())
					.reservationCartItemId(reservationCartItem.getId())
					.reservationItemName(reservationCartItem.getReservationItem().getReservationItemName())
					.reservationItemOptionName(reservationCartItem.getReservationItemOption().getOptionName())
					.sendType(reservationCartItem.getSendType())
					.cartReservationInfo(
						CartReservationInfoRequest.builder(reservationCartItem.getCartReservationInfo()).build());
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

	}

}
