package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

public interface ShippingOrderItemRepository extends JpaRepository<ShippingOrderItem, UUID> {
	Optional<ShippingOrderItem> findByShippingOrderSelectedOptions(ShippingOrderSelectedOption shippingOrderSelectedOption);

	@Query(value = "SELECT * FROM shipping_order_item JOIN item ON item.id = shipping_order_item.item_id WHERE shipping_order_item.id = :id", nativeQuery = true)
	Optional<ShippingOrderItem> findByShippingOrderItemId(Long id);
}
