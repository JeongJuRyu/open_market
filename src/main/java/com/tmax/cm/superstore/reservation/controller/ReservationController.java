package com.tmax.cm.superstore.reservation.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.GetReservableDatesDto;
import com.tmax.cm.superstore.reservation.dto.GetReservableDatesDto.Response.GetReservableDateDto;
import com.tmax.cm.superstore.reservation.dto.GetReservableTimesDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

    // TODO API 명세 변경 및 자세한 구현하기

    @GetMapping("/year/{year}/month/{month}")
    public ResponseDto<GetReservableDatesDto.Response> GetReservableDates(@PathVariable Integer year,
            @PathVariable Integer month) {

        GetReservableDatesDto.Response response = GetReservableDatesDto.Response.builder().reservableDates(new ArrayList<>()).build();

        LocalDate firstDateOfMonth = LocalDate.of(year, month, 1);
        // firstDateOfCalendar: 달력상의 첫 날짜 ex. 2022년 8월 달력의 첫 날짜는 7월 31일
        LocalDate firstDateOfCalendar = firstDateOfMonth.minusDays((firstDateOfMonth.getDayOfWeek().getValue() % 7));
        LocalDate today = LocalDate.now();

        // 달력에 5주 표시
        for (int i = 0; i < 7 * 5; i++) {
            LocalDate currentDate = firstDateOfCalendar.plusDays(i);
            response.getReservableDates().add(GetReservableDateDto.builder()
                    .date(currentDate)
                    .isReservable(currentDate.isAfter(today))
                    .build());
        }

        return new ResponseDto<>(ResponseCode.RESERVATION_DATE_READ, response);
    }

    @GetMapping("/date/{date}/time")
    public ResponseDto<GetReservableTimesDto.Response> GetReservableTimes(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        GetReservableTimesDto.Response response = GetReservableTimesDto.Response.builder().build();

        return new ResponseDto<>(ResponseCode.RESERVATION_TIME_READ, response);
    }
}
