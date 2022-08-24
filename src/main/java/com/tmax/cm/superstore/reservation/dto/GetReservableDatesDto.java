package com.tmax.cm.superstore.reservation.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class GetReservableDatesDto {

    @Builder
    @Getter
    public static class Response {

        private List<GetReservableDateDto> reservableDates;

        @Builder
        @Getter
        public static class GetReservableDateDto {

            private LocalDate date;

            private Boolean isReservable;
        }
    }
}
