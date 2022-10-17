package com.tmax.cm.superstore.pickup.repository;

import com.tmax.cm.superstore.pickup.entity.PickUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PickUpRepository extends JpaRepository<PickUp, UUID> {
}
