package com.tmax.cm.superstore.item.dto.mapper;


import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.dto.UpdateItemDto;
import com.tmax.cm.superstore.item.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface UpdateItemDtoMapper {

    @Mapping(target = "itemId", source = "id")
    UpdateItemDto.Response toResponse(Item item);

}
