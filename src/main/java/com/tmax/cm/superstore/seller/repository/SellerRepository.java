package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
	Seller findSellerBySellerId(UUID sellerId);
}
