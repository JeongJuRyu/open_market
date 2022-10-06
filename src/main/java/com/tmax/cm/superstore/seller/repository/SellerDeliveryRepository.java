package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellerDeliveryRepository extends JpaRepository<SellerDelivery, UUID> {
}
