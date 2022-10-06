package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SellerDeliveryRepository extends JpaRepository<SellerDelivery, UUID> {

	List<SellerDelivery> findAllBySellerId(Seller sellerId);
}
