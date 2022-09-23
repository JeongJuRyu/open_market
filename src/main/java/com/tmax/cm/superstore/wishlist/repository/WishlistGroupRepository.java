package com.tmax.cm.superstore.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistGroupRepository extends JpaRepository<WishlistGroup, Long> {
    @Query("SELECT wg FROM WishlistGroup wg ORDER BY wg.position ACS")
    List<WishlistGroup> findAllAsc();
}