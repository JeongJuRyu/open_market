package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class FindReservationItemListDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<ReservationItemList> reservationItemList;

		public static ResponseBuilder builder(List<ReservationItemList> reservationItemList) {
			return ResponseBuilder()
				.reservationItemList(reservationItemList);
		}

		@Getter
		@Builder(builderMethodName = "ReservationItemListBuilder")
		public static class ReservationItemList{
			private UUID reservationItemId;
			private String reservationItemName;
			private String reservationItemDescription;
			private String reservationItemNotice;
			private Integer allowReservationNumberPerInterval;
			private String reservationInterval;
			private LocalTime startTime;
			private LocalTime endTime;

			public static ReservationItemListBuilder builder(ReservationItem reservationItem){
				return ReservationItemListBuilder()
					.reservationItemId(reservationItem.getReservationItemId())
					.reservationItemName(reservationItem.getReservationItemName())
					.reservationItemDescription(reservationItem.getReservationItemDescription())
					.reservationItemNotice(reservationItem.getReservationItemNotice())
					.allowReservationNumberPerInterval(reservationItem.getAllowReservationNumberPerInterval())
					.reservationInterval(reservationItem.getReservationInterval())
					.startTime(reservationItem.getStartTime())
					.endTime(reservationItem.getEndTime());
			}
		}
	}
}
