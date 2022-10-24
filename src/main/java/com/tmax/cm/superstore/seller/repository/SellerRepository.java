package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
	Seller findSellerBySellerId(UUID sellerId);

	Seller findSellerByLoginId(String loginId);

	List<Seller> findAll();

	@Query("select s from Seller s join Item i join ShippingOrderItem soi "
		+ "where soi.id = :shippingOrderItemId")
	Optional<Seller> findForShippingOrderInquiry(Long shippingOrderItemId);
}
