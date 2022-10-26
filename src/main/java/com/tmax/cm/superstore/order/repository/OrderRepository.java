package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	@Query(value = "select * from orders join users on users.id = orders.user_id join review on review.user_id = users.id where review.id = :reviewId", nativeQuery = true)
	List<Order> findByReviewId(UUID reviewId);

	@Query(value = "select * from orders O join shipping_order so on so.order_id = o.id join shipping_order_item soi"
		+ " on soi.shipping_order_id = so.id join shipping_order_selected_option as soso on soi.id = soso.shipping_order_item_id where soso.id = :selectedOptionId", nativeQuery = true)
	Optional<Order> findByShippingSelectedOption(UUID selectedOptionId);

	@Query(value = "select * from orders O join shipping_order so on so.order_id = o.id join shipping_order_item soi"
		+ " on soi.shipping_order_id = so.id join shipping_order_selected_option as soso on soi.id = soso.shipping_order_item_id where soso.id = :selectedOptionId", nativeQuery = true)
	Optional<Order> findByPickupSelectedOption(UUID selectedOptionId);

	@Query(value = "select * from orders O join delivery_order do on do.order_id = o.id join shipping_order_item soi"
		+ " on soi.delivery_order_id = do.id join shipping_order_selected_option as soso on soi.id = soso.shipping_order_item_id where SOSO.ID = :selectedOptionId", nativeQuery = true)
	Optional<Order> findBySelectedOptionWIthDelivery(UUID selectedOptionId);
}