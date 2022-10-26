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

	@Query(value = "select * from seller as s join item i on i.seller_seller_id = s.seller_id "
		+ "join shipping_order_item as soi on soi.item_id = i.id where i.id = :itemId" , nativeQuery = true)
	Optional<Seller> findForShippingOrderInquiry(UUID itemId);

	@Query(value = "select * from seller as s join item i on i.seller_seller_id = s.seller_id "
		+ "join pickup_order_item as poi on poi.item_id = i.id where i.id = :itemId" , nativeQuery = true)
	Optional<Seller> findForPickupOrderInquiry(UUID itemId);

}
