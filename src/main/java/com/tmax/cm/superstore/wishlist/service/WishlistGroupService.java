package com.tmax.cm.superstore.wishlist.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.wishlist.dto.mapper.GetWishlistGroupAllDtoMapper;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import com.tmax.cm.superstore.wishlist.repository.WishlistItemRepository;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupOrderDto;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistItemMoveDto;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final GetWishlistGroupAllDtoMapper getWishlistGroupAllDtoMapper;

    @Transactional
    public WishlistGroup create(CreateWishlistGroupDto createWishlistGroupDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long totalItemCount = this.wishlistGroupRepository.count();

        WishlistGroup wishlistGroup = WishlistGroup.builder()
                .name(createWishlistGroupDto.getWishListGroupName())
                .position(totalItemCount.intValue() + 1)
                .build();

        this.wishlistGroupRepository.save(wishlistGroup);
        return wishlistGroup;
    }

    @Transactional
    public List<WishlistGroup> readAll() {
        return this.wishlistGroupRepository.findAll();
    }

    @Transactional
    public WishlistGroup update(Long wishlistGroupId, UpdateWishlistGroupDto updateWishlistGroupDto) {

        WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(wishlistGroupId)
                .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));

        wishlistGroup.setName(updateWishlistGroupDto.getWishListGroupName());
        return wishlistGroup;
    }

    @Transactional
    public void delete(Long groupId) {
        this.wishlistGroupRepository.deleteById(groupId);
    }

    @Transactional
    public void updateOrder(UpdateWishlistGroupOrderDto updateWishlistGroupOrderDto) {
        Long newIndex = 1L;
        for (Long groupId : updateWishlistGroupOrderDto.getGroupIds()) {
            WishlistGroup wishlistGroup = this.wishlistGroupRepository.findById(groupId)
                    .orElseThrow(() -> EntityNotFoundException.of(WishlistGroup.class));

            wishlistGroup.setPosition(newIndex.intValue());
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