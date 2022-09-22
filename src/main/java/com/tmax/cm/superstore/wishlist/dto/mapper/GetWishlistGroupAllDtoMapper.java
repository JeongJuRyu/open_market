package com.tmax.cm.superstore.wishlist.dto.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.wishlist.dto.GetWishlistGroupAllDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;

import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Mapper(config = CommonMapperConfig.class)
public interface GetWishlistGroupAllDtoMapper {

    default GetWishlistGroupAllDto.Response toResponse(List<WishlistGroup> wishlistGroups) {
        return GetWishlistGroupAllDto.Response.builder()
                .wishlistGroupDtoList(toWishlistGroupAllDto(wishlistGroups))
                .build();
    }

    @Mapping(target = "wishlistGroupDtoList", source = "wishlistGroups")
    List<GetWishlistGroupAllDto.Response.GetWishlistGroupsDto> toWishlistGroupAllDto(List<WishlistGroup> wishlistGroups);

    default Integer getTotalItems(WishlistGroup wishlistGroup) {
        return wishlistGroup.getWishlistItems().size();
    }

    default GetWishlistGroupAllDto.Response.GetWishlistGroupsDto toWishlistGroupDtos(WishlistGroup wishlistGroup) {
        return this.toWishlistGroupDto(wishlistGroup, getTotalItems(wishlistGroup));
    }

    @Mapping(target = "groupId", source = "wishlistGroup.id")
    @Mapping(target = "wishlistGroupName", source = "wishlistGroup.name")
    @Mapping(target = "wishlistGroupThumbnailURL", ignore = true)
    @Mapping(target = "totalItemCount", source = "count")
    GetWishlistGroupAllDto.Response.GetWishlistGroupsDto toWishlistGroupDto(WishlistGroup wishlistGroup, Integer count);
}
