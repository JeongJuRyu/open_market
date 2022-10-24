package com.tmax.cm.superstore.cart.repository.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartReservationInfoRepository extends JpaRepository<CartReservationInfo, UUID> {

	CartReservationInfo findByReservationId(UUID reservationId);
}
