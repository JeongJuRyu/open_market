package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	@Query(value = "SELECT * FROM ORDERS JOIN USERS JOIN REVIEW WHERE REVIEW.ID = :reviewId", nativeQuery = true)
	Optional<Order> findByReviewId(UUID reviewId);

	@Query(value = "SELECT * FROM ORDERS JOIN SHIPPING_ORDER JOIN SHIPPING_ORDER_ITEM"
		+ " JOIN SHIPPING_ORDER_SELECTED_OPTION AS SOSO WHERE SOSO.ID = :selectedOptionId", nativeQuery = true)
	Optional<Order> findBySelectedOption(UUID selectedOptionId);
}
