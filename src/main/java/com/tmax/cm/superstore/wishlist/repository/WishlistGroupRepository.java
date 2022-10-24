package com.tmax.cm.superstore.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishlistGroupRepository extends JpaRepository<WishlistGroup, Long> {
    @Query("SELECT wg FROM WishlistGroup wg where wg.user.id = :userId ORDER BY wg.position")
    List<WishlistGroup> findAllAsc(UUID userId);
}