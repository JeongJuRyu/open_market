package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.seller.entity.Seller;

public interface PickupOrderRepository extends JpaRepository<PickupOrder, UUID> {

    List<PickupOrder> findBySeller(Seller seller);
}
