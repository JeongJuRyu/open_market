package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CreateReservationItemImageDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private UUID reservationItemImageId;
		private String imageName;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemImageId;
		private String imageName;

		public static ResponseBuilder builder(ReservationItemImage reservationItemImage) {
			return ResponseBuilder()
				.reservationItemImageId(reservationItemImage.getReservationItemImageId())
				.imageName(reservationItemImage.getImageName());
		}
	}
}
