package com.tmax.cm.superstore.cart.repository.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationCartItemRepository extends JpaRepository<ReservationCartItem, UUID> {
}
