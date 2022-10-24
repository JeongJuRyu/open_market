package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;

public interface PickupOrderItemRepository extends JpaRepository<PickupOrderItem, UUID> {
	Optional<PickupOrderItem> findByPickupOrderSelectedOptions(PickupOrderSelectedOption pickupOrderSelectedOption);

	@Query(value = "SELECT * FROM pickup_order_item JOIN item ON item.id = pickup_order_item.item_id WHERE pickup_order_item.id = :id", nativeQuery = true)
	Optional<PickupOrderItem> findByPickupOrderItemId(Long id);
}
