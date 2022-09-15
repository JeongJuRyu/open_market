package com.tmax.cm.superstore.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

public class FindPossibleReservationByTime {

	@Getter
	@Builder(builderMethodName = "ResponseBuilder")
	public static class Response {
		private List<LocalTime> possibleTimes;

		public static ResponseBuilder builder(
			List<LocalTime> possibleReservationTime) {
			return ResponseBuilder()
				.possibleTimes(possibleReservationTime);
		}
	}
}
