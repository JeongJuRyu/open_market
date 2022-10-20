package com.tmax.cm.superstore.wishlist.controller;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.wishlist.dto.*;
import com.tmax.cm.superstore.wishlist.dto.mapper.GetIsWishlistItemDtoMapper;
import com.tmax.cm.superstore.wishlist.dto.mapper.GetWishlistGroupAllDtoMapper;
import com.tmax.cm.superstore.wishlist.dto.mapper.GetWishlistItemDtoMapper;
import com.tmax.cm.superstore.wishlist.dto.mapper.PostWishlistGroupDtoMapper;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import com.tmax.cm.superstore.wishlist.service.WishlistGroupService;
import com.tmax.cm.superstore.wishlist.service.WishlistItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {

    private final WishlistGroupService wishlistGroupService;
    private final WishlistItemService wishlistItemService;
    private final GetWishlistGroupAllDtoMapper getWishlistGroupAllDtoMapper;
    private final GetWishlistItemDtoMapper getWishlistItemDtoMapper;
    private final PostWishlistGroupDtoMapper postWishlistGroupDtoMapper;
    private final GetIsWishlistItemDtoMapper getIsWishlistItemDtoMapper;


    @GetMapping("/wishlistGroup")
    public ResponseDto<GetWishlistGroupAllDto.Response> getWishlistGroupAll(@AuthenticationPrincipal User user) {
        List<WishlistGroup> wishlistGroups = this.wishlistGroupService.readAll(user);
        GetWishlistGroupAllDto.Response response = this.getWishlistGroupAllDtoMapper.toResponse(wishlistGroups);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_READ, response);
    }

    @PostMapping("/wishlistGroup")
    public ResponseDto<PostCreateWishlistGroupDto.Response> createWishlistGroup(@Valid @RequestBody PostCreateWishlistGroupDto.Request groupDto, @AuthenticationPrincipal User user) {
        WishlistGroup wishlistGroup = this.wishlistGroupService.create(groupDto, user);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_CREATE, this.postWishlistGroupDtoMapper.toResponse(wishlistGroup));
    }

    @PostMapping("/wishlistItem")
    public ResponseDto<Void> createWishlistItem(@Valid @RequestBody PostCreateWishlistItemDto.Request itemDto, @AuthenticationPrincipal User user) {
        WishlistItem wishlistItem = this.wishlistItemService.create(itemDto, user);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_CREATE, null);
    }

    @GetMapping("/wishlistItem/isItem/{itemId}")
    public ResponseDto<GetIsWishlistItemDto.Response> isWishlistItem(@Valid @PathVariable UUID itemId, @AuthenticationPrincipal User user) {
        Boolean result = this.wishlistItemService.checkItem(itemId, user);
        GetIsWishlistItemDto.Response response = this.getIsWishlistItemDtoMapper.toResponse(result);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_CHECK_READ, response);
    }

    @GetMapping("/wishlistItem")
    public ResponseDto<GetWishlistItemDto.Response> getWishlistItem(@Valid @RequestParam(name = "wishlistGroupId", required = false) Long groupId, @AuthenticationPrincipal User user) {
        List<WishlistGroup> wishlistGroups = this.wishlistGroupService.readAll(user);
        List<WishlistItem> wishlistItems = (groupId == null) ? (this.wishlistItemService.readAll(user)) : (this.wishlistItemService.findByGroupId(groupId));
        GetWishlistItemDto.Response response = this.getWishlistItemDtoMapper.toResponse(wishlistItems, wishlistGroups);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_READ, response);
    }

    @PatchMapping("/wishlistGroup/{wishlistGroupId}")
    public ResponseDto<Void> updateWishlistGroup(@Valid @PathVariable Long wishlistGroupId, @RequestBody PatchUpdateWishlistGroupDto.Request groupDto) {
        this.wishlistGroupService.update(wishlistGroupId, groupDto);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_UPDATE, null);
    }

    @PatchMapping("/wishlistGroup/listOrder")
    public ResponseDto<Void> changeGroupOrder(@Valid @RequestBody PatchUpdatePositionWishlistGroupDto.Request groupDto) {
        this.wishlistGroupService.updateOrder(groupDto);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_ORDER_UPDATE, null);
    }

    @PatchMapping("/wishlistItem/move")
    public ResponseDto<Void> updateWishlistItemGroup(@Valid @RequestBody PatchUpdateMoveItemDto.Request groupDto) {
        this.wishlistGroupService.updateItemMove(groupDto);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_GROUP_MOVE_UPDATE, null);
    }

    @DeleteMapping("/wishlistGroup/{wishlistGroupId}")
    public ResponseDto<Void> deleteWishlistGroup(@Valid @PathVariable Long wishlistGroupId) {
        this.wishlistGroupService.delete(wishlistGroupId);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_DELETE, null);
    }

    @DeleteMapping("/wishlistItem")
    public ResponseDto<Void> deleteWishlistItem(@Valid @RequestBody DeleteWishlistItemsDto.Request request) {
        this.wishlistItemService.delete(request);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_DELETE, null);
    }

    @DeleteMapping("/wishlistItem/item")
    public ResponseDto<Void> deleteWishlistItemByItemId(@Valid @RequestBody DeleteWishlistItemDto.Request request) {
        this.wishlistItemService.deleteByItemId(request);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_DELETE, null);
    }
}
