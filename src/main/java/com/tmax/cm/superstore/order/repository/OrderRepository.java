package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	@Query(value = "SELECT * FROM ORDERS JOIN USERS ON USERS.ID = ORDERS.USER_ID JOIN REVIEW ON REVIEW.USER_ID = USERS.ID WHERE REVIEW.ID = :reviewId", nativeQuery = true)
	Optional<Order> findByReviewId(UUID reviewId);

	@Query(value = "SELECT * FROM ORDERS O JOIN SHIPPING_ORDER SO ON SO.ORDER_ID = O.ID JOIN SHIPPING_ORDER_ITEM SOI"
		+ " ON SOI.SHIPPING_ORDER_ID = SO.ID JOIN SHIPPING_ORDER_SELECTED_OPTION AS SOSO ON SOI.ID = SOSO.SHIPPING_ORDER_ITEM_ID WHERE SOSO.ID = :selectedOptionId", nativeQuery = true)
	Optional<Order> findBySelectedOption(UUID selectedOptionId);
}
