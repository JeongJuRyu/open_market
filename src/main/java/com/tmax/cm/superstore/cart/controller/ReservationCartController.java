package com.tmax.cm.superstore.cart.controller;

import com.tmax.cm.superstore.cart.dto.reservationCart.*;
import com.tmax.cm.superstore.cart.service.CartService;
import com.tmax.cm.superstore.cart.service.reservationCart.ReservationCartItemService;
import com.tmax.cm.superstore.cart.service.reservationCart.ReservationCartService;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.MakeReservationDto;
import com.tmax.cm.superstore.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart/reservation")
public class ReservationCartController {

	private final CartService cartService;
	private final ReservationCartService reservationCartService;
	private final ReservationCartItemService reservationCartItemService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<PostReservationCartItemDto.Response>> postCreateCartItem(@AuthenticationPrincipal User user,
		@Valid @RequestBody PostReservationCartItemDto.Request postReservationCartItemRequestDto) throws Exception {

		return ResponseEntity.ok().body(reservationCartItemService.create(user, postReservationCartItemRequestDto));
	}

	@GetMapping("/list")
	public ResponseEntity<ResponseDto<GetReservationCartItemListDto.Response>> getFindCartItemList(@AuthenticationPrincipal User user) throws Exception{
		return ResponseEntity.ok().body(reservationCartItemService.findList(user));
	}

	@GetMapping("/detail/{reservationCartItemId}")
	public ResponseEntity<ResponseDto<GetReservationCartItemDto.Response>> getFindCartItem(@AuthenticationPrincipal User user, @PathVariable UUID reservationCartItemId) throws Exception{
		return ResponseEntity.ok().body(reservationCartItemService.find(user, reservationCartItemId));
	}

	@PostMapping("/make/{reservationCartItemId}")
	public ResponseEntity<ResponseDto<MakeReservationDto.Response>> postCartReservation(@AuthenticationPrincipal User user, @PathVariable
		UUID reservationCartItemId, @Valid @RequestBody MakeReservationDto.Request makeReservationRequestDto) throws Exception{
		return ResponseEntity.ok().body(reservationCartItemService.makeReservation(user, reservationCartItemId, makeReservationRequestDto));
	}

	@PatchMapping("/update/{reservationCartItemId}")
	public ResponseEntity<ResponseDto<PatchReservationCartItem.Response>> patchUpdateCartItem(@AuthenticationPrincipal User user, @PathVariable
		UUID reservationCartItemId, @Valid @RequestBody PatchReservationCartItem.Request patchReservationCartItemRequestDto) throws Exception{
		return ResponseEntity.ok().body(reservationCartItemService.update(user, reservationCartItemId, patchReservationCartItemRequestDto));
	}

	@DeleteMapping("/delete/{reservationCartItemId}")
	public ResponseEntity<ResponseDto<DeleteReservationCartItem.Response>> deleteCartItem(@AuthenticationPrincipal User user, @PathVariable UUID reservationCartItemId) throws Exception{
		return ResponseEntity.ok().body(reservationCartItemService.delete(user, reservationCartItemId));
	}
}
