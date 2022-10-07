package com.tmax.cm.superstore.cart.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findByIdIn(List<UUID> ids);
}
