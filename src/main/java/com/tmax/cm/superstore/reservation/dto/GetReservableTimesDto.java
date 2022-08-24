package com.tmax.cm.superstore.reservation.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

public class GetReservableTimesDto {

    @Builder
    @Getter
    public static class Response {

        // TODO 기본값 없이 로직으로 동작하도록 구현 변경
        @Builder.Default
        private List<GetReservableTimeDto> reservableTimes = new ArrayList<>() {
            {
                add(GetReservableTimeDto.builder().time(LocalTime.of(10, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(11, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(12, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(13, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(14, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(15, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(16, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(17, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(18, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(19, 0)).isReservable(true).build());
                add(GetReservableTimeDto.builder().time(LocalTime.of(20, 0)).isReservable(true).build());
            }
        };

        @Builder
        @Getter
        public static class GetReservableTimeDto {

            private LocalTime time;

            private Boolean isReservable;
        }
    }
}
