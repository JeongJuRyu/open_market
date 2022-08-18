package com.tmax.cm.superstore.item.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.entity.Item;

@Mapper(config = CommonMapperConfig.class)
public interface PostItemDtoMapper {
    
    @Mapping(target = "itemId", source = "id")
    PostItemDto.Response toResponse(Item item);
}
