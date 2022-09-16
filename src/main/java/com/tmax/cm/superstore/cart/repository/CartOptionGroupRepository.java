package com.tmax.cm.superstore.cart.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.cart.entity.CartOptionGroup;

public interface CartOptionGroupRepository extends JpaRepository<CartOptionGroup, UUID> {

}
