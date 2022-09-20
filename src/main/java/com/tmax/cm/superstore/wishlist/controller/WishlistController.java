package com.tmax.cm.superstore.wishlist.controller;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.wishlist.dto.PatchUpdateMoveItemDto;
import com.tmax.cm.superstore.wishlist.dto.PatchUpdatePositionWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.dto.PatchUpdateWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.dto.PostCreateWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.service.WishlistGroupService;
import com.tmax.cm.superstore.wishlist.service.WishlistItemService;
import com.tmax.cm.superstore.wishlist.service.dto.CreateWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.service.dto.UpdateWishlistGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {

    private final WishlistGroupService wishlistGroupService;
    private final WishlistItemService wishlistItemService;

    @PostMapping("/wishlistGroup")
    public ResponseDto<Void> createWishlistGroup(@Valid @RequestBody PostCreateWishlistGroupDto.Request groupDto) {
        this.wishlistGroupService.create(groupDto);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_CREATE, null);
    }

    @PatchMapping("/wishlistGroup/{wishlistGroupId}")
    public ResponseDto<Void> updateWishlistGroup(@Valid @PathVariable Long GroupId, @RequestBody PatchUpdateWishlistGroupDto.Request groupDto) {
        this.wishlistGroupService.update(GroupId, groupDto);
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
    public ResponseDto<Void> deleteWishlistGroup(@Valid @PathVariable Long groupId) {
        this.wishlistGroupService.delete(groupId);
        return new ResponseDto<>(ResponseCode.WISHLIST_GROUP_DELETE, null);
    }

    @DeleteMapping("/wishlistItem/{wishlistItemId}")
    public ResponseDto<Void> deleteWishlistItem(@Valid @PathVariable Long itemId) {
        this.wishlistItemService.delete(itemId);
        return new ResponseDto<>(ResponseCode.WISHLIST_ITEM_DELETE, null);
    }
}
