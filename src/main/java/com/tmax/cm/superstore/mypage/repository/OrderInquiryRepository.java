package com.tmax.cm.superstore.mypage.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

public interface OrderInquiryRepository extends JpaRepository<OrderInquiry, UUID> {
	@Query("select oi from OrderInquiry oi where oi.user.id = :userId "
		+ "and oi.createdAt >= :startDate")
	List<OrderInquiry> findAllByUserId(UUID userId, LocalDateTime startDate);

	Optional<OrderInquiry> findByShippingOrderSelectedOption(ShippingOrderSelectedOption shippingOrderSelectedOption);

	Optional<OrderInquiry> findByPickupOrderSelectedOption(PickupOrderSelectedOption pickupOrderSelectedOption);

	@Query(value = "select * from order_inquiry as oi join shipping_order_selected_option as soso on oi.shipping_order_selected_option_id = soso.id "
		+ "join seller as s on s.seller_id = soso.seller_seller_id "
		+ "where s.seller_id = :sellerId", nativeQuery = true)
	List<OrderInquiry> findForSellerOrderInquiryOfShipping(UUID sellerId);

	@Query(value = "select * from order_inquiry as oi join pickup_order_selected_option as poso on oi.pickup_order_selected_option_id = poso.id "
		+ "join seller as s on s.seller_id = poso.seller_seller_id "
		+ "where s.seller_id = :sellerId", nativeQuery = true)
	List<OrderInquiry> findForSellerOrderInquiryOfPickup(UUID sellerId);

}
