package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

public class ModifyReservationItemDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private String reservationItemName;
		private String reservationItemPrice;
		private String reservationItemDescription;
		private String reservationItemNotice;
		private Integer allowReservationNumberPerInterval;
		private String reservationInterval;
		private LocalTime startTime;
		private LocalTime endTime;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemId;
		private String reservationItemName;
		private String reservationItemPrice;
		private String reservationItemDescription;
		private String reservationItemNotice;
		private Integer allowReservationNumberPer30;
		private String reservationInterval;
		private Boolean isDeleted;

		public static ResponseBuilder builder(
			ReservationItem reservationItem) {
			return ResponseBuilder()
				.reservationItemId(reservationItem.getReservationItemId())
				.reservationItemName(reservationItem.getReservationItemName())
				.reservationItemPrice(reservationItem.getReservationItemPrice())
				.reservationItemDescription(reservationItem.getReservationItemDescription())
				.reservationItemNotice(reservationItem.getReservationItemNotice())
				.allowReservationNumberPer30(reservationItem.getAllowReservationNumberPerInterval())
				.reservationInterval(reservationItem.getReservationInterval())
				.isDeleted(reservationItem.isDeleted());
		}
	}
}
