package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ModifyReservationDto {

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
		private String reservationItemName;
		private String reservationItemOptionName;
//		private UUID reservationItemId;
//		private UUID reservationItemOptionId;

		public static ResponseBuilder builder(Optional<Reservation> reservation) {
			return ResponseBuilder()
				.reservationTime(reservation.get().getReservationTime())
				.numberOfPeople(reservation.get().getNumberOfPeople())
				.customerRequest(reservation.get().getCustomerRequest())
				.reservationItemName(reservation.get().getReservationItemId().getReservationItemName())
				.reservationItemOptionName(reservation.get().getReservationItemOptionId().getOptionName());
//				.reservationItemId(reservation.get().getReservationItemId().getReservationItemId())
//				.reservationItemOptionId(reservation.get().getReservationItemOptionId().getOptionId());
		}
	}
}
