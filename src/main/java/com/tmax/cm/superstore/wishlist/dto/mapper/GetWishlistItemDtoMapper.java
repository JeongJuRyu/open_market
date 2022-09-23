package com.tmax.cm.superstore.wishlist.dto.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.wishlist.dto.GetWishlistItemDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface GetWishlistItemDtoMapper {

    default GetWishlistItemDto.Response toResponse(List<WishlistItem> wishlistItems) {
        return GetWishlistItemDto.Response.builder()
                .wishlistItemDtos(toWishlistItems(wishlistItems))
                .build();
    }

    @Mapping(target = "wishlistItemDtos", source = "wishlistItems")
    List<GetWishlistItemDto.Response.WishlistItemDto> toWishlistItems(List<WishlistItem> wishlistItems);

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

}
