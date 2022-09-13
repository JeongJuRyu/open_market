package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CreateReservationItemOptionDto {

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request {
		private String optionName;
		private String optionPrice;
		private String optionDescription;
	}

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private UUID optionId;
		private String optionName;
		private String optionPrice;
		private String optionDescription;
		private UUID reservationItemId;

		public static ResponseBuilder builder(ReservationItemOption reservationItemOption,
			ReservationItem reservationItem) {
			return ResponseBuilder()
				.optionId(reservationItemOption.getOptionId())
				.optionName(reservationItemOption.getOptionName())
				.optionPrice(reservationItemOption.getOptionPrice())
				.optionDescription(reservationItemOption.getOptionDescription())
				.reservationItemId(reservationItem.getReservationItemId());
		}
	}
}
