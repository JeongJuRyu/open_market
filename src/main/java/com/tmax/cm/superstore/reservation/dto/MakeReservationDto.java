package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class MakeReservationDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private LocalDateTime reservationTime;
		private Integer numberOfPeople;
		private String customerRequest;
		private UUID reservationItemId;
		private UUID reservationItemOptionId;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private LocalDateTime reservationTime;
		private Integer numberOfPeople;
		private String customerRequest;
		private UUID reservationItemId;
		private UUID reservationItemOptionId;

		public static ResponseBuilder builder(Reservation reservation) {
			return ResponseBuilder()
				.reservationTime(reservation.getReservationTime())
				.numberOfPeople(reservation.getNumberOfPeople())
				.customerRequest(reservation.getCustomerRequest())
				.reservationItemId(reservation.getReservationItemId().getReservationItemId())
				.reservationItemOptionId(reservation.getReservationItemOptionId().getOptionId());
		}
	}
}
