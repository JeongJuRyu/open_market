package com.tmax.cm.superstore.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByWishlistGroupId(Long wishlistGroupId);

    Optional<WishlistItem> findByItemId(UUID itemId);
}