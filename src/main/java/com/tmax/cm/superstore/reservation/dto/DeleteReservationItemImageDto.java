package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class DeleteReservationItemImageDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemImageId;
		private String fileId;
		private String imageName;

		public static ResponseBuilder builder(
			ReservationItemImage reservationItemImage) {
			return ResponseBuilder()
				.reservationItemImageId(reservationItemImage.getReservationItemImageId())
				.fileId(reservationItemImage.getFileId())
				.imageName(reservationItemImage.getImageName());
		}
	}
}
