package com.tmax.cm.superstore.cart.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.cart.entity.CartOption;

public interface CartOptionRepository extends JpaRepository<CartOption, UUID> {

}
