package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class DeleteReservationItemDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response{
		private UUID reservationItemId;
		private Boolean isDeleted;

		public static ResponseBuilder builder(ReservationItem reservationItem){
			return ResponseBuilder()
				.reservationItemId(reservationItem.getReservationItemId())
				.isDeleted(reservationItem.isDeleted());
		}
	}
}
