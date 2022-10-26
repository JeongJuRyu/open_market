package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.mypage.entity.Review;
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

	@Query(value = "SELECT * FROM SELLER AS S JOIN ITEM I ON I.SELLER_SELLER_ID = S.SELLER_ID "
		+ "JOIN SHIPPING_ORDER_ITEM AS SOI ON SOI.ITEM_ID = I.ID WHERE I.ID = :itemId" , nativeQuery = true)
	Optional<Seller> findForShippingOrderInquiry(UUID itemId);

	@Query(value = "SELECT * FROM SELLER AS S JOIN ITEM I ON I.SELLER_SELLER_ID = S.SELLER_ID "
		+ "JOIN PICKUP_ORDER_ITEM AS SOI ON SOI.ITEM_ID = I.ID WHERE I.ID = :itemId" , nativeQuery = true)
	Optional<Seller> findForPickupOrderInquiry(UUID itemId);

}
