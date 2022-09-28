package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

public class FindReservationItemDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID reservationItemId;
		private String reservationItemName;
		private String reservationItemPrice;
		private String reservationItemDescription;
		private String reservationItemNotice;
		private Integer allowReservationNumberPerInterval;
		private String reservationInterval;
		private LocalTime startTime;
		private LocalTime endTime;

		public static ResponseBuilder builder(ReservationItem reservationItem) {
			return ResponseBuilder()
				.reservationItemId(reservationItem.getReservationItemId())
				.reservationItemName(reservationItem.getReservationItemName())
				.reservationItemPrice(reservationItem.getReservationItemPrice())
				.reservationItemDescription(reservationItem.getReservationItemDescription())
				.reservationItemNotice(reservationItem.getReservationItemNotice())
				.allowReservationNumberPerInterval(reservationItem.getAllowReservationNumberPerInterval())
				.reservationInterval(reservationItem.getReservationInterval())
				.startTime(reservationItem.getStartTime())
				.endTime(reservationItem.getEndTime());
		}
	}
}
