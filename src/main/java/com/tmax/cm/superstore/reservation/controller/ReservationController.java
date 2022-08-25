package com.tmax.cm.superstore.reservation.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping("/{sellerId}/item/create")
	public ResponseEntity<ResponseDto<CreateReservationItemDto.Response>> createReservationItem(@PathVariable UUID sellerId,
		@Valid @RequestBody CreateReservationItemDto.Request createReservationItemRequestDto) throws Exception {
		return ResponseEntity.ok().body(reservationService.createReservationItem(sellerId, createReservationItemRequestDto));
	}

}
