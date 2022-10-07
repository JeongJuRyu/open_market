package com.tmax.cm.superstore.seller.repository;

import com.tmax.cm.superstore.seller.entity.Business;
import com.tmax.cm.superstore.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {

	Business findBusinessBySellerId(Seller sellerId);
}
