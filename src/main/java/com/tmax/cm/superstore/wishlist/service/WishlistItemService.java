package com.tmax.cm.superstore.wishlist.service;

import com.tmax.cm.superstore.error.exception.EntityNotFoundException;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.wishlist.dto.DeleteWishlistItemsDto;
import com.tmax.cm.superstore.wishlist.dto.PostCreateWishlistItemDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import com.tmax.cm.superstore.wishlist.error.exception.WishlistItemAlreadyExistsException;
import com.tmax.cm.superstore.wishlist.repository.WishlistItemRepository;
import com.tmax.cm.superstore.wishlist.service.dto.DeleteWishlistItemByItemIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void delete(DeleteWishlistItemsDto.Request request) {
        for (Long wishlistItemId : request.getWishlistItemIds()) {
            WishlistItem wishlistItem = this.wishlistItemRepository.findById(wishlistItemId)
                    .orElseThrow(() -> EntityNotFoundException.of(WishlistItem.class));
            wishlistItem.setIsDeleted(true);
        }
    }

    @Transactional
    public List<WishlistItem> findByGroupId(Long groupId) {
        return this.wishlistItemRepository.findByWishlistGroupId(groupId);
    }

    public List<WishlistItem> readAll(User user) {
        return this.wishlistItemRepository.findByUserId(user.getId());
    }

    @Transactional
    public WishlistItem create(PostCreateWishlistItemDto.Request itemDto, User user) {
        if(this.checkItem(itemDto.getItemId(), user))
            throw new WishlistItemAlreadyExistsException();

        Item item =  this.itemRepository.findById(itemDto.getItemId())
                .orElseThrow(ItemNotFoundException::new);
        WishlistGroup wishlistGroup = user.getWishlistGroups().get(0);

        WishlistItem wishlistItem = WishlistItem.builder()
                .item(item)
                .build();

        wishlistItem.setGroup(wishlistGroup);
        this.wishlistItemRepository.save(wishlistItem);
        return wishlistItem;
    }

    @Transactional
    public Boolean checkItem(UUID itemId, User user) {
        return this.wishlistItemRepository.findByUserId(user.getId()).stream().anyMatch(wishlistItem ->
                wishlistItem.getItem().getId().equals(itemId));
    }

    @Transactional
    public void deleteByItemId(DeleteWishlistItemByItemIdDto requestDto) {
        WishlistItem wishlistItem = this.wishlistItemRepository.findByItemId(requestDto.getItemId())
                .orElseThrow(() -> EntityNotFoundException.of(WishlistItem.class));
        wishlistItem.setIsDeleted(true);
    }
}
