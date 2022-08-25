package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalTime;
import java.util.UUID;

public class CreateReservationItemDto {

	@Getter
	@AllArgsConstructor
	@Builder
	public static class Request{
		private String reservationItemName;
		private String reservationItemDescription;
		private String reservationItemNotice;
		private Integer allowReservationNumberPer30;
		private String reservationInterval;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response{
		private UUID reservationItemId;
		private String reservationItemName;
		private String reservationItemDescription;
		private String reservationItemNotice;
		private Integer allowReservationNumberPer30;
		private String reservationInterval;
		private Boolean isDeleted;
		private UUID sellerId;

		public static ResponseBuilder builder(ReservationItem reservationItem, Seller seller){
			return ResponseBuilder()
				.reservationItemId(reservationItem.getReservationItemId())
				.reservationItemName(reservationItem.getReservationItemName())
				.reservationItemDescription(reservationItem.getReservationItemDescription())
				.reservationItemNotice(reservationItem.getReservationItemNotice())
				.allowReservationNumberPer30(reservationItem.getAllowReservationNumberPer30())
				.reservationInterval(reservationItem.getReservationInterval())
				.isDeleted(reservationItem.isDeleted())
				.sellerId(seller.getSellerId());
		}
	}
}
