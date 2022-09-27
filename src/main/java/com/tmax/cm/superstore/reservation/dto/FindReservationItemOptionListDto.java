package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public class FindReservationItemOptionListDto {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<ReservationItemOptionList> reservationItemOptionList;

		public static ResponseBuilder builder(List<ReservationItemOptionList> reservationItemOptionList) {
			return ResponseBuilder()
				.reservationItemOptionList(reservationItemOptionList);
		}

		@Getter
		@Builder(builderMethodName = "ReservationItemOptionListBuilder")
		public static class ReservationItemOptionList {
			private UUID optionId;
			private String optionName;
			private String optionPrice;
			private String optionDescription;

			public static ReservationItemOptionListBuilder builder(
				ReservationItemOption reservationItemOption) {
				return ReservationItemOptionListBuilder()
					.optionId(reservationItemOption.getOptionId())
					.optionName(reservationItemOption.getOptionName())
					.optionPrice(reservationItemOption.getOptionPrice())
					.optionDescription(reservationItemOption.getOptionDescription());
			}
		}
	}
}
