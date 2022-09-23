package com.tmax.cm.superstore.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByWishlistGroupId(Long wishlistGroupId);
}