package com.tmax.cm.superstore.wishlist.service;

import com.tmax.cm.superstore.wishlist.repository.WishlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;

    @Transactional
    public void delete(Long itemId) {
        this.wishlistItemRepository.deleteById(itemId);
    }
}
