package com.tmax.cm.superstore.cart.repository.reservationCart;

import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCart;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationCartItemRepository extends JpaRepository<ReservationCartItem, UUID> {

	ReservationCartItem findReservationCartItemById(UUID Id);
	List<ReservationCartItem> findAllByReservationCartIdAndIsDeletedFalse(ReservationCart reservationCartId);

}
