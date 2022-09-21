package com.tmax.cm.superstore.wishlist.service;

import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import com.tmax.cm.superstore.wishlist.repository.WishlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;

    @Transactional
    public void delete(Long itemId) {
        this.wishlistItemRepository.deleteById(itemId);
    }

    @Transactional
    public List<WishlistItem> findByGroupId(Long groupId) {
        return this.wishlistItemRepository.findByWishlistGroupId(groupId);
    }

    public List<WishlistItem> readAll() {
        return this.wishlistItemRepository.findAll();
    }
}
