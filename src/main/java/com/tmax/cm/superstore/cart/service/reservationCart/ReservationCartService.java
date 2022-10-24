package com.tmax.cm.superstore.cart.service.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCart;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.cart.repository.reservationCart.ReservationCartRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationCartService {

	private final ReservationCartRepository reservationCartRepository;

	@Transactional(rollbackFor = Exception.class)
	public ReservationCart createReservationCart(CartType cartType, User user) {
		ReservationCart reservationCart = ReservationCart.builder(cartType, user).build();
		reservationCartRepository.save(reservationCart);

		return reservationCart;
	}

	@Transactional(rollbackFor = Exception.class)
	public ReservationCart readReservationCart(User user, CartType cartType) {
		ReservationCart reservationCart = reservationCartRepository.findTopByCartTypeAndUser(cartType, user);

		if (reservationCart == null) {
			reservationCart = this.createReservationCart(cartType, user);
		}

		return reservationCart;
	}
}
