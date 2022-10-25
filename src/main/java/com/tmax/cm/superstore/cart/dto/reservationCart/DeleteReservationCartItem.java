package com.tmax.cm.superstore.cart.dto.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class DeleteReservationCartItem {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationCartId;
		private UUID reservationCartItemId;
		private Boolean isDeleted;

		public static ResponseBuilder builder(
			ReservationCartItem reservationCartItem) {
			return ResponseBuilder()
				.reservationCartId(reservationCartItem.getReservationCartId().getId())
				.reservationCartItemId(reservationCartItem.getId())
				.isDeleted(reservationCartItem.getIsDeleted());
		}
	}
}
