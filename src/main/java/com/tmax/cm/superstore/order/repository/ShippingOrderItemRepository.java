package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

public interface ShippingOrderItemRepository extends JpaRepository<ShippingOrderItem, UUID> {
	Optional<ShippingOrderItem> findByShippingOrderSelectedOptions(ShippingOrderSelectedOption shippingOrderSelectedOption);
}
