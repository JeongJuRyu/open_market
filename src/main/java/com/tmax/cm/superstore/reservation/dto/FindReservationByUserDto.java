package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindReservationByUserDto {

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
			private String sellerName;
			private String sellerEmail;
			private String sellerPhoneNum;
			private LocalDate reservationDay;
			private LocalDateTime reservationTime;
			private Integer numberOfPeople;
			private String customerRequest;
			private String reservationItemName;
			private String reservationItemOptionName;

			public static ReservationList.ReservationListBuilder builder(
				Seller seller, Optional<Reservation> reservation) {
				return ReservationListBuilder()
					.reservationId(reservation.get().getReservationId())
					.sellerName(seller.getSellerName())
					.sellerEmail(seller.getSellerEmail())
					.sellerPhoneNum(seller.getSellerPhoneNum())
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