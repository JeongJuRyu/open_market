package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.seller.entity.Seller;

public interface ShippingOrderSelectedOptionRepository extends JpaRepository<ShippingOrderSelectedOption, UUID> {

	@Query("select soso from ShippingOrderSelectedOption soso "
			+ "join fetch OrderOptionGroup oog "
			+ "join fetch OrderOption oo "
			+ "where soso.id = :id")
	Optional<ShippingOrderSelectedOption> findForReview(UUID id);

	Optional<ShippingOrderSelectedOption> findByIdAndSeller(UUID id, Seller seller);

	@Query(value = "SELECT soso.* FROM shipping_order_selected_option soso\n"
			+ "JOIN shipping_order_item soi ON soi.id = soso.shipping_order_item_id\n"
			+ "JOIN shipping_order so ON so.id = soi.shipping_order_id\n"
			+ "JOIN orders o ON o.id = so.order_id\n"
			+ "WHERE soso.id = UUID_TO_BIN(:selectedOrderOptionId) AND o.user_user_id = UUID_TO_BIN(:userId)\n"
			+ "UNION\n"
			+ "SELECT soso.* FROM shipping_order_selected_option soso\n"
			+ "JOIN shipping_order_item soi ON soi.id = soso.shipping_order_item_id\n"
			+ "JOIN delivery_order do ON do.id = soi.delivery_order_id\n"
			+ "JOIN orders o ON o.id = do.order_id\n"
			+ "WHERE soso.id = UUID_TO_BIN(:selectedOrderOptionId) AND o.user_user_id = UUID_TO_BIN(:userId)\n"
			+ "LIMIT 1", nativeQuery = true)
	Optional<ShippingOrderSelectedOption> findByIdAndUserId(UUID selectedOrderOptionId, UUID userId);

}
