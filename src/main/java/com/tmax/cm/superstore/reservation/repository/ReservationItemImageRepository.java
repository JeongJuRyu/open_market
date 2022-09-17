package com.tmax.cm.superstore.reservation.repository;

import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationItemImageRepository extends JpaRepository<ReservationItemImage, UUID> {
	ReservationItemImage findReservationItemImageByReservationItemId(ReservationItem reservationItemId);
}
