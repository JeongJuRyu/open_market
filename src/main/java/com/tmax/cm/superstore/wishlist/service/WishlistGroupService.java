package com.tmax.cm.superstore.wishlist.service;

import java.util.List;

import javax.transaction.Transactional;

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

    @Transactional
    public WishlistGroup create(CreateWishlistGroupDto createWishlistGroupDto) {

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

        return wishlistGroup;
    }
}