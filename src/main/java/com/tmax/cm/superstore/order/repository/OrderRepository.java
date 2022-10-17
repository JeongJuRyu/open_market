package com.tmax.cm.superstore.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
