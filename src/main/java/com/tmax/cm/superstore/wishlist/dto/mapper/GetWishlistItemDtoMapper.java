package com.tmax.cm.superstore.wishlist.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.wishlist.dto.GetWishlistItemDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;

@Mapper(config = CommonMapperConfig.class)
public interface GetWishlistItemDtoMapper {

    default GetWishlistItemDto.Response toResponse(List<WishlistItem> wishlistItems,
            List<WishlistGroup> wishlistGroups) {
        return GetWishlistItemDto.Response.builder()
                .wishlistItemDtos(toWishlistItems(wishlistItems))
                .wishlistGroupDtos(toWishlistGroups(wishlistGroups))
                .build();
    }

    @Mapping(target = "wishlistItemDtos", source = "wishlistItems")
    List<GetWishlistItemDto.Response.WishlistItemDto> toWishlistItems(List<WishlistItem> wishlistItems);

    @Mapping(target = "wishlistGroupsDtos", source = "wishlistGroups")
    List<GetWishlistItemDto.Response.WishlistGroupDto> toWishlistGroups(List<WishlistGroup> wishlistGroups);

    default String getFileId(WishlistItem wishlistItem) {
        if(wishlistItem.getItem().getItemImages().isEmpty())
            return null;
        return wishlistItem.getItem().getItemImages().get(0).getFileId();
    }

    default GetWishlistItemDto.Response.WishlistItemDto toWishlistItem(WishlistItem wishlistItem) {
        return this.toWishlistItem(wishlistItem, getFileId(wishlistItem));
    }

    @Mapping(target = "wishlistItemId", source = "wishlistItem.id")
    @Mapping(target = "wishlistGroupId", source = "wishlistItem.wishlistGroup.id")
    @Mapping(target = "wishlistGroupName", source = "wishlistItem.wishlistGroup.name")
    @Mapping(target = "shopId", source = "wishlistItem.item.seller.sellerId")
    @Mapping(target = "shopName", source = "wishlistItem.item.seller.sellerName")
    @Mapping(target = "itemId", source = "wishlistItem.item.id")
    @Mapping(target = "itemThumbnailURL", source = "fileId")
    @Mapping(target = "itemName", source = "wishlistItem.item.name")
    @Mapping(target = "itemPrice", source = "wishlistItem.item.price")
    @Mapping(target = "categoryId", source = "wishlistItem.item.category.id")
    @Mapping(target = "categoryName", source = "wishlistItem.item.category.name")
    GetWishlistItemDto.Response.WishlistItemDto toWishlistItem(WishlistItem wishlistItem, String fileId);

    default Integer getTotalItems(WishlistGroup wishlistGroup) {
        return wishlistGroup.getWishlistItems().size();
    }

    default String getFileId(WishlistGroup wishlistGroup) {
        if(wishlistGroup.getWishlistItems().isEmpty())
            return null;
        if(wishlistGroup.getWishlistItems().get(0).getItem().getItemImages().isEmpty())
            return null;
        return wishlistGroup.getWishlistItems().get(0).getItem().getItemImages().get(0).getFileId();
    }

    default GetWishlistItemDto.Response.WishlistGroupDto toWishlistGroupDtos(WishlistGroup wishlistGroup) {
        return this.toWishlistGroupDto(wishlistGroup, getTotalItems(wishlistGroup), getFileId(wishlistGroup));
    }

    @Mapping(target = "wishlistGroupId", source = "wishlistGroup.id")
    @Mapping(target = "wishlistGroupName", source = "wishlistGroup.name")
    @Mapping(target = "wishlistGroupThumbnailURL", source = "fileId")
    @Mapping(target = "totalItemCount", source = "count")
    GetWishlistItemDto.Response.WishlistGroupDto toWishlistGroupDto(WishlistGroup wishlistGroup, Integer count, String fileId);

}
