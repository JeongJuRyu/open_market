package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;

public interface PickupOrderItemRepository extends JpaRepository<PickupOrderItem, UUID> {
	Optional<PickupOrderItem> findByPickupOrderSelectedOptions(PickupOrderSelectedOption pickupOrderSelectedOption);
}
