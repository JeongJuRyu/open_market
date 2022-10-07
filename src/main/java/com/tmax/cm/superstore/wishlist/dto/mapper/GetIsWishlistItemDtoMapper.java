package com.tmax.cm.superstore.wishlist.dto.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.wishlist.dto.GetIsWishlistItemDto;
import com.tmax.cm.superstore.wishlist.dto.PostCreateWishlistGroupDto;
import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface GetIsWishlistItemDtoMapper {

    @Mapping(target = "result", source = "result")
    GetIsWishlistItemDto.Response toResponse(Boolean result);
}
