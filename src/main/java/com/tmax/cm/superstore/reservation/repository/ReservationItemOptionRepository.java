package com.tmax.cm.superstore.reservation.repository;

import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationItemOptionRepository extends JpaRepository<ReservationItemOption, UUID> {
	ReservationItemOption findReservationItemOptionByReservationItemId(UUID reservationItemId);
}
