package com.tmax.cm.superstore.wishlist.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;

public interface WishlistGroupRepository extends JpaRepository<WishlistGroup, Long> {

}