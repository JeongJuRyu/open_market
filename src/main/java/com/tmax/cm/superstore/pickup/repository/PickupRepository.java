package com.tmax.cm.superstore.pickup.repository;

import com.tmax.cm.superstore.pickup.entity.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PickupRepository extends JpaRepository<Pickup, UUID> {
}
