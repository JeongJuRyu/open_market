package com.tmax.cm.superstore.reservation.dto;

import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class ModifyReservationItemOptionDto {

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
		private UUID reservationItemOptionId;
		private String optionName;
		private String optionPrice;
		private String optionDescription;
		private Boolean isDeleted;

		public static ResponseBuilder builder(ReservationItemOption reservationItemOption) {
			return ResponseBuilder()
				.reservationItemOptionId(reservationItemOption.getOptionId())
				.optionName(reservationItemOption.getOptionName())
				.optionPrice(reservationItemOption.getOptionPrice())
				.optionDescription(reservationItemOption.getOptionDescription())
				.isDeleted(reservationItemOption.isDeleted());
		}
	}
}
