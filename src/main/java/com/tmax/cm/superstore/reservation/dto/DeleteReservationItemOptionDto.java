package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class DeleteReservationItemOptionDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemOptionId;
		private Boolean isDeleted;

		public static ResponseBuilder builder(ReservationItemOption reservationItemOption) {
			return ResponseBuilder()
				.reservationItemOptionId(reservationItemOption.getOptionId())
				.isDeleted(reservationItemOption.isDeleted());
		}
	}
}
