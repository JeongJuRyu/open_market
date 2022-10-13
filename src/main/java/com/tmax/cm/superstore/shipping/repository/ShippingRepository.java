package com.tmax.cm.superstore.shipping.repository;

import com.tmax.cm.superstore.shipping.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
}
