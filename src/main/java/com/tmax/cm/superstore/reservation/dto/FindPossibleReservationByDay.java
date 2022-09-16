package com.tmax.cm.superstore.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class FindPossibleReservationByDay {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<LocalDate> possibleDays;

		public static ResponseBuilder builder(
			List<LocalDate> possibleReservationDate) {
			return ResponseBuilder()
				.possibleDays(possibleReservationDate);
		}
	}
}
