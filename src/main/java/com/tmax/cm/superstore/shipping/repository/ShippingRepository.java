package com.tmax.cm.superstore.ship.repository;

import com.tmax.cm.superstore.ship.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipRepository extends JpaRepository<Ship, UUID> {
}
