package com.tmax.cm.superstore.wishlist.dto.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.wishlist.dto.GetWishlistGroupAllDto;
import com.tmax.cm.superstore.wishlist.dto.GetWishlistItemDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface GetWishlistItemDtoMapper {

    default GetWishlistItemDto.Response toResponse(List<WishlistItem> wishlistItems, List<WishlistGroup> wishlistGroups) {
        return GetWishlistItemDto.Response.builder()
                .wishlistItemDtos(toWishlistItems(wishlistItems))
                .wishlistGroupDtos(toWishlistGroups(wishlistGroups))
                .build();
    }

    @Mapping(target = "wishlistItemDtos", source = "wishlistItems")
    List<GetWishlistItemDto.Response.WishlistItemDto> toWishlistItems(List<WishlistItem> wishlistItems);

    @Mapping(target = "wishlistGroupsDtos", source = "wishlistGroups")
    List<GetWishlistItemDto.Response.WishlistGroupDto> toWishlistGroups(List<WishlistGroup> wishlistGroups);

    @Mapping(target = "wishlistItemId", source = "wishlistItem.id")
    @Mapping(target = "wishlistGroupId", source = "wishlistItem.wishlistGroup.id")
    @Mapping(target = "wishlistGroupName", source = "wishlistItem.wishlistGroup.name")
    @Mapping(target = "shopId", source = "wishlistItem.item.shop.id")
    @Mapping(target = "shopName", source = "wishlistItem.item.shop.name")
    @Mapping(target = "itemId", source = "wishlistItem.item.id")
    @Mapping(target = "itemThumbnailURL", ignore = true)
    @Mapping(target = "itemName", source = "wishlistItem.item.name")
    @Mapping(target = "itemPrice", source = "wishlistItem.item.price")
    @Mapping(target = "categoryId", source = "wishlistItem.item.category.id")
    @Mapping(target = "categoryName", source = "wishlistItem.item.category.name")
    GetWishlistItemDto.Response.WishlistItemDto toWishlistItem(WishlistItem wishlistItem);

    default Integer getTotalItems(WishlistGroup wishlistGroup) {
        return wishlistGroup.getWishlistItems().size();
    }

    default GetWishlistItemDto.Response.WishlistGroupDto toWishlistGroupDtos(WishlistGroup wishlistGroup) {
        return this.toWishlistGroupDto(wishlistGroup, getTotalItems(wishlistGroup));
    }

    @Mapping(target = "wishlistGroupId", source = "wishlistGroup.id")
    @Mapping(target = "wishlistGroupName", source = "wishlistGroup.name")
    @Mapping(target = "wishlistGroupThumbnailURL", ignore = true)
    @Mapping(target = "totalItemCount", source = "count")
    GetWishlistItemDto.Response.WishlistGroupDto toWishlistGroupDto(WishlistGroup wishlistGroup, Integer count);

}
