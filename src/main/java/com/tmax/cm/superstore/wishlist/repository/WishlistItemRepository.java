package com.tmax.cm.superstore.wishlist.repository;

import com.tmax.cm.superstore.item.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    List<WishlistItem> findByWishlistGroupId(Long wishlistGroupId);

    Optional<WishlistItem> findByItemId(UUID itemId);

    @Query("select wi from WishlistItem wi where wi.wishlistGroup.user.id = :userId")
    List<WishlistItem> findByUserId(UUID userId);
}