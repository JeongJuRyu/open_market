package com.tmax.cm.superstore.reservation.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemImageDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemOptionDto;
import com.tmax.cm.superstore.reservation.dto.FindPossibleReservationByDay;
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
	public ResponseEntity<ResponseDto<CreateReservationItemDto.Response>> createReservationItem(
		@PathVariable UUID sellerId,
		@Valid @RequestBody CreateReservationItemDto.Request createReservationItemRequestDto) throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.createReservationItem(sellerId, createReservationItemRequestDto));
	}

	@PostMapping("/{reservationItemId}/item/image/create")
	public ResponseEntity<ResponseDto<CreateReservationItemImageDto.Response>> createReservationItemImage(
		@PathVariable UUID reservationItemId,
		@Valid @RequestBody CreateReservationItemImageDto.Request createReservationItemImageRequestDto)
		throws Exception {
		return ResponseEntity.ok().body(
			reservationService.createReservationItemImage(reservationItemId, createReservationItemImageRequestDto));
	}

	@PostMapping("/{reservationItemId}/item/option/create")
	public ResponseEntity<ResponseDto<CreateReservationItemOptionDto.Response>> createReservationItemOption(
		@PathVariable UUID reservationItemId,
		@Valid @RequestBody CreateReservationItemOptionDto.Request createReservationItemOptionRequestDto)
		throws Exception {
		return ResponseEntity.ok().body(
			reservationService.createReservationItemOption(reservationItemId, createReservationItemOptionRequestDto));
	}

	@GetMapping("/{reservationItemId}/possible/day")
	public ResponseEntity<ResponseDto<FindPossibleReservationByDay.Response>> findPossibleReservationByDay(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findPossibleReservationByDay(reservationItemId));
	}

	//예약가능 시간 조회
}
