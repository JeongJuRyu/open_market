package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.seller.entity.Seller;

public interface ShippingOrderSelectedOptionRepository extends JpaRepository<ShippingOrderSelectedOption, UUID> {

    Optional<ShippingOrderSelectedOption> findByIdAndSeller(UUID id, Seller seller);
}
