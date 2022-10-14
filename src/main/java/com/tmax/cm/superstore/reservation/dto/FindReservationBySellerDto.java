package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.user.entities.User;
import lombok.Builder;
import lombok.Getter;

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
			private String userName;
			private String userEmail;
			private String userPhoneNum;
			private LocalDateTime reservationTime;
			private Integer numberOfPeople;
			private String customerRequest;
			private UUID reservationItemId;
			private UUID reservationItemOptionId;

			public static ReservationListBuilder builder(Optional<User> user, Optional<Reservation> reservation) {
				return ReservationListBuilder()
					.userName(user.get().getName())
					.userEmail(user.get().getEmail())
					.userPhoneNum(user.get().getPhoneNum())
					.reservationTime(reservation.get().getReservationTime())
					.numberOfPeople(reservation.get().getNumberOfPeople())
					.customerRequest(reservation.get().getCustomerRequest())
					.reservationItemId(reservation.get().getReservationItemId().getReservationItemId())
					.reservationItemOptionId(reservation.get().getReservationItemOptionId().getOptionId());
			}
		}

	}
}
