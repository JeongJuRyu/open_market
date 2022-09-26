package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class FindReservationItemImageDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemImageId;

		public static ResponseBuilder builder(ReservationItemImage reservationItemImage) {
			return ResponseBuilder()
				.reservationItemImageId(reservationItemImage.getReservationItemImageId());
		}
	}
}
