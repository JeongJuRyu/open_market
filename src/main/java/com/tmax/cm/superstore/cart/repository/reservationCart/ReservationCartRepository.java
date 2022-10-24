package com.tmax.cm.superstore.cart.repository.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCart;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationCartRepository extends JpaRepository<ReservationCart, UUID> {

	ReservationCart findTopByCartType(CartType cartType);
	ReservationCart findTopByCartTypeAndUser(CartType cartType, User user);
}
