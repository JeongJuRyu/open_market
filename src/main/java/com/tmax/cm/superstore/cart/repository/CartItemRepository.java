package com.tmax.cm.superstore.cart.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.user.entities.User;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    Optional<CartItem> findByIdAndUser(UUID id, User user);

    List<CartItem> findByUserAndIdIn(User user, List<UUID> ids);
}
