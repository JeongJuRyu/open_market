package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

public interface ShippingOrderItemRepository extends JpaRepository<ShippingOrderItem, UUID> {
	Optional<ShippingOrderItem> findByShippingOrderSelectedOptions(ShippingOrderSelectedOption shippingOrderSelectedOption);

	@Query(value = "SELECT * FROM shipping_order_item JOIN item ON item.id = shipping_order_item.item_id WHERE shipping_order_item.id = :id", nativeQuery = true)
	Optional<ShippingOrderItem> findByShippingOrderItemId(Long id);

	// @Query(value = "SELECT * FROM SHIPPING_ORDER_ITEM AS SOI JOIN SHIPPING_ORDER_SELECTED_OPTION AS SOSO ON SOSO.SHIPPING_ORDER_ITEM_ID = SOI.ID "
	// 	+ "JOIN ORDER_INQUIRY AS OI ON OI.SHIPPING_ORDER_SELECTED_OPTION_ID = SOSO.ID", nativeQuery = true)
	// Optional<ShippingOrderItem> findForSellerOrderInquiry(UUID id);
}
