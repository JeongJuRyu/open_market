package com.tmax.cm.superstore.shop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.shop.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, UUID>{
    
}
