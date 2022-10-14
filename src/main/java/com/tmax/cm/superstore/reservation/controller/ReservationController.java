package com.tmax.cm.superstore.reservation.controller;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.*;
import com.tmax.cm.superstore.reservation.dto.GetReservableDatesDto.Response.GetReservableDateDto;
import com.tmax.cm.superstore.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	/**
	 * 예약 상품
	 */
	@PostMapping("/{sellerId}/item")
	public ResponseEntity<ResponseDto<CreateReservationItemDto.Response>> createReservationItem(
		@PathVariable UUID sellerId,
		@Valid @RequestBody CreateReservationItemDto.Request createReservationItemRequestDto) throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.createReservationItem(sellerId, createReservationItemRequestDto));
	}

	@PatchMapping("/{reservationItemId}/item")
	public ResponseEntity<ResponseDto<ModifyReservationItemDto.Response>> modifyReservationItem(
		@PathVariable UUID reservationItemId,
		@Valid @RequestBody ModifyReservationItemDto.Request modifyReservationItemRequestDto) throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.modifyReservationItem(reservationItemId, modifyReservationItemRequestDto));
	}

	@DeleteMapping("/{reservationItemId}/item")
	public ResponseEntity<ResponseDto<DeleteReservationItemDto.Response>> deleteReservationItem(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.deleteReservationItem(reservationItemId));
	}

	@GetMapping("/item/all")
	public ResponseEntity<ResponseDto<FindReservationItemsDto.Response>> findReservationItems() throws Exception{
		return ResponseEntity.ok().body(reservationService.findReservationItems());
	}

	@GetMapping("{reservationItemId}/item")
	public ResponseEntity<ResponseDto<FindReservationItemDto.Response>> findReservationItem(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findReservationItem(reservationItemId));
	}

	@GetMapping("/{sellerId}/item/list")
	public ResponseEntity<ResponseDto<FindReservationItemListDto.Response>> findReservationItemList(
		@PathVariable UUID sellerId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findReservationItemList(sellerId));
	}

	/**
	 * 예약 상품 이미지
	 */
	@PostMapping("/{reservationItemId}/item/image")
	public ResponseEntity<ResponseDto<CreateReservationItemImageDto.Response>> createReservationItemImage(
		@PathVariable UUID reservationItemId,
		@Valid @RequestBody CreateReservationItemImageDto.Request createReservationItemImageRequestDto)
		throws Exception {
		return ResponseEntity.ok().body(
			reservationService.createReservationItemImage(reservationItemId, createReservationItemImageRequestDto));
	}

	@DeleteMapping("/{reservationItemId}/item/image")
	public ResponseEntity<ResponseDto<DeleteReservationItemImageDto.Response>> deleteReservationItemImage(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.deleteReservationItemImage(reservationItemId));
	}

	@GetMapping("/{reservationItemId}/item/image")
	public ResponseEntity<ResponseDto<FindReservationItemImageDto.Response>> findReservationItemImage(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findReservationItemImage(reservationItemId));
	}

	/**
	 * 예약 상품 옵션
	 */
	@PostMapping("/{reservationItemId}/item/option")
	public ResponseEntity<ResponseDto<CreateReservationItemOptionDto.Response>> createReservationItemOption(
		@PathVariable UUID reservationItemId,
		@Valid @RequestBody CreateReservationItemOptionDto.Request createReservationItemOptionRequestDto)
		throws Exception {
		return ResponseEntity.ok().body(
			reservationService.createReservationItemOption(reservationItemId, createReservationItemOptionRequestDto));
	}

	@PatchMapping("/{reservationItemOptionId}/item/option")
	public ResponseEntity<ResponseDto<ModifyReservationItemOptionDto.Response>> modifyReservationItemOption(
		@PathVariable UUID reservationItemOptionId,
		@Valid @RequestBody ModifyReservationItemOptionDto.Request modifyReservationItemOptionRequestDto)
		throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.modifyReservationItemOption(reservationItemOptionId,
				modifyReservationItemOptionRequestDto));
	}

	@DeleteMapping("/{reservationItemOptionId}/item/option")
	public ResponseEntity<ResponseDto<DeleteReservationItemOptionDto.Response>> deleteReservationItemOption(
		@PathVariable UUID reservationItemOptionId) throws Exception {
		return ResponseEntity.ok().body(reservationService.deleteReservationItemOption(reservationItemOptionId));
	}

	@GetMapping("/{reservationItemId}/option")
	public ResponseEntity<ResponseDto<FindReservationItemOptionListDto.Response>> findReservationItemOptionList(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findReservationItemOptionList(reservationItemId));
	}

	/**
	 * 예약
	 */
	@GetMapping("/{reservationItemId}/possible/day")
	public ResponseEntity<ResponseDto<FindPossibleReservationByDay.Response>> findPossibleReservationByDay(
		@PathVariable UUID reservationItemId) throws Exception {
		return ResponseEntity.ok().body(reservationService.findPossibleReservationByDay(reservationItemId));
	}

	@GetMapping("/{reservationItemId}/possible/time")
	public ResponseEntity<ResponseDto<FindPossibleReservationByTime.Response>> findPossibleReservationByTime(
		@PathVariable UUID reservationItemId, @RequestParam(value = "reservationDay") String reservationDay)
		throws Exception {
		return ResponseEntity.ok()
			.body(reservationService.findPossibleReservationByTime(reservationItemId, reservationDay));
	}

	@PostMapping("/make")
	public ResponseEntity<ResponseDto<MakeReservationDto.Response>> makeReservation(
		@Valid @RequestBody MakeReservationDto.Request makeReservationRequestDto) throws Exception {
		return ResponseEntity.ok().body(reservationService.makeReservation(makeReservationRequestDto));
	}

	@GetMapping("/{sellerId}/list")
	public ResponseEntity<ResponseDto<FindReservationBySellerDto.Response>> findReservationBySeller(@PathVariable UUID sellerId) throws Exception{
		return ResponseEntity.ok().body(reservationService.findReservationBySeller(sellerId));
	}

	@GetMapping("/list/user")
	public ResponseEntity<ResponseDto<FindReservationByUserDto.Response>> findReservationByUser() throws Exception{
		return ResponseEntity.ok().body(reservationService.findReservationByUser());
	}

	/**
	 * 장바구니 구현을 위한 임시 예약
	 */
	@GetMapping("/year/{year}/month/{month}")
	public ResponseDto<GetReservableDatesDto.Response> GetReservableDates(@PathVariable Integer year,
		@PathVariable Integer month) {

		GetReservableDatesDto.Response response = GetReservableDatesDto.Response.builder()
			.reservableDates(new ArrayList<>()).build();

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
