package com.tmax.cm.superstore.cart.controller;

import com.tmax.cm.superstore.cart.dto.reservationCart.PostReservationCartItemDto;
import com.tmax.cm.superstore.cart.service.CartService;
import com.tmax.cm.superstore.cart.service.reservationCart.ReservationCartItemService;
import com.tmax.cm.superstore.cart.service.reservationCart.ReservationCartService;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart/reservation")
public class ReservationCartController {

	private final CartService cartService;
	private final ReservationCartService reservationCartService;
	private final ReservationCartItemService reservationCartItemService;

	@PostMapping("/cartItem")
	public ResponseEntity<ResponseDto<PostReservationCartItemDto.Response>> postCreateCartItem(@AuthenticationPrincipal User user,
		@Valid @RequestBody PostReservationCartItemDto.Request postReservationCartItemRequestDto) throws Exception {

		return ResponseEntity.ok().body(reservationCartItemService.create(user, postReservationCartItemRequestDto));
	}
}
