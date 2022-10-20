package com.tmax.cm.superstore.wishlist.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import com.tmax.cm.superstore.wishlist.repository.WishlistItemRepository;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupOrderDto;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistItemMoveDto;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.error.exception.EntityNotFoundException;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.repository.WishlistGroupRepository;
import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WishlistGroupService {

    private final WishlistGroupRepository wishlistGroupRepository;
    private final WishlistItemRepository wishlistItemRepository;

    @Transactional
    public WishlistGroup create(CreateWishlistGroupDto createWishlistGroupDto, User user) {
        Long totalItemCount = this.wishlistGroupRepository.count();

        WishlistGroup wishlistGroup = WishlistGroup.builder()
                .name(createWishlistGroupDto.getWishlistGroupName())
                .position(totalItemCount.intValue() + 1)
                .build();

        wishlistGroup.setUpUser(user);
        this.wishlistGroupRepository.save(wishlistGroup);
        return wishlistGroup;
    }

    @Transactional
    public List<WishlistGroup> readAll(User user) {
        return this.wishlistGroupRepository.findAllAsc(user.getId());
    }

    @Transactional
    public WishlistGroup update(Long wishlistGroupId, UpdateWishlistGroupDto updateWishlistGroupDto) {
        WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(wishlistGroupId)
                .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));

        wishlistGroup.setName(updateWishlistGroupDto.getWishlistGroupName());
        return wishlistGroup;
    }

    @Transactional
    public void delete(Long groupId) {
        WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(groupId)
                .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));
        wishlistGroup.deleteGroup();
        wishlistGroup.setIsDeleted(true);
    }

    @Transactional
    public void updateOrder(UpdateWishlistGroupOrderDto updateWishlistGroupOrderDto) {
        Integer newIndex = 1;
        for (Long groupId : updateWishlistGroupOrderDto.getWishlistGroupIds()) {
            WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(groupId)
                    .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));

            wishlistGroup.setPosition(newIndex);
            newIndex++;
        }
    }

    @Transactional
    public void updateItemMove(UpdateWishlistItemMoveDto updateWishlistItemMoveDto) {
        WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(updateWishlistItemMoveDto.getTargetWishlistGroupId())
                .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));
        for (Long wishlistItemId : updateWishlistItemMoveDto.getWishlistItemIds()) {
            WishlistItem wishlistItem = this.wishlistItemRepository.findById(wishlistItemId)
                    .orElseThrow(() -> EntityNotFoundException.of(WishlistItemService.class));

            wishlistItem.setGroup(wishlistGroup);
        }
    }
}