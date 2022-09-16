package com.tmax.cm.superstore.reservation.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.*;
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

	@GetMapping("/{sellerId}/item")
	public ResponseEntity<ResponseDto<FindReservationItemListDto.Response>> findReservationItemList(@PathVariable UUID sellerId) throws Exception{
		return ResponseEntity.ok().body(reservationService.findReservationItemList(sellerId));
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

	@GetMapping("/{reservationItemId}/possible/time")
	public ResponseEntity<ResponseDto<FindPossibleReservationByTime.Response>> findPossibleReservationByTime(
		@PathVariable UUID reservationItemId, @RequestParam(value = "reservationDay") String reservationDay)
		throws Exception {
		return ResponseEntity.ok().body(reservationService.findPossibleReservationByTime(reservationItemId, reservationDay));
	}

	@PostMapping("/make")
	public ResponseEntity<ResponseDto<MakeReservationDto.Response>> makeReservation(
		@Valid @RequestBody MakeReservationDto.Request makeReservationRequestDto) throws Exception {
		return ResponseEntity.ok().body(reservationService.makeReservation(makeReservationRequestDto));
	}
}
