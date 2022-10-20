package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.seller.entity.Seller;

public interface PickupOrderSelectedOptionRepository extends JpaRepository<PickupOrderSelectedOption, UUID> {

    Optional<PickupOrderSelectedOption> findByIdAndSeller(UUID id, Seller seller);
}
