package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.user.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindReservationBySellerDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<ReservationList> reservationList;

		public static ResponseBuilder builder(List<ReservationList> reservationList) {
			return ResponseBuilder()
				.reservationList(reservationList);
		}

		@Getter
		@Builder(builderMethodName = "ReservationListBuilder")
		public static class ReservationList {
			private UUID reservationId;
			private String userName;
			private String userEmail;
			private String userPhoneNum;
			private LocalDate reservationDay;
			private LocalDateTime reservationTime;
			private Integer numberOfPeople;
			private String customerRequest;
			private String reservationItemName;
			private String reservationItemOptionName;

			public static ReservationListBuilder builder(Optional<User> user, Optional<Reservation> reservation) {
				return ReservationListBuilder()
					.reservationId(reservation.get().getReservationId())
					.userName(user.get().getName())
					.userEmail(user.get().getEmail())
					.userPhoneNum(user.get().getPhoneNum())
					.reservationDay(reservation.get().getReservationDay())
					.reservationTime(reservation.get().getReservationTime())
					.numberOfPeople(reservation.get().getNumberOfPeople())
					.customerRequest(reservation.get().getCustomerRequest())
					.reservationItemName(reservation.get().getReservationItemId().getReservationItemName())
					.reservationItemOptionName(reservation.get().getReservationItemOptionId().getOptionName());
			}
		}
	}
}
