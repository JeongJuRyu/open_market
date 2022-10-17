package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
		private LocalDate reservationDay;
		private LocalDateTime reservationTime;
		private Integer numberOfPeople;
		private String customerRequest;
		private String reservationItemName;
		private String reservationItemOptionName;

		public static ResponseBuilder builder(Reservation reservation) {
			return ResponseBuilder()
				.reservationDay(reservation.getReservationDay())
				.reservationTime(reservation.getReservationTime())
				.numberOfPeople(reservation.getNumberOfPeople())
				.customerRequest(reservation.getCustomerRequest())
				.reservationItemName(reservation.getReservationItemId().getReservationItemName())
				.reservationItemOptionName(reservation.getReservationItemOptionId().getOptionName());
		}
	}
}
