package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.seller.entity.Seller;

public interface VisitOrderRepository extends JpaRepository<VisitOrder, UUID> {

    List<VisitOrder> findBySeller(Seller seller);
}
