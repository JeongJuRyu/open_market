package com.tmax.cm.superstore.reservation.repository;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationItemRepository extends JpaRepository<ReservationItem, UUID> {
	ReservationItem findReservationItemByReservationItemId(UUID reservationItemId);

	List<ReservationItem> findAllBySellerIdAndIsDeletedFalse(Seller sellerId);
}
