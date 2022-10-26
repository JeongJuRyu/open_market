package com.tmax.cm.superstore.cart.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.user.entities.User;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    
    Cart findTopByUserAndCartType(User user, CartType cartType);
}
