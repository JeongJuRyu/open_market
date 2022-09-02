package com.tmax.cm.superstore.item.dto.mapper;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.config.CommonMapperConfig;

import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(config= CommonMapperConfig.class)
public interface GetItemAllByCategoryDtoMapper {

    default GetItemAllByCategoryDto.Response toResponse(List<Item> items) {
        return GetItemAllByCategoryDto.Response.builder()
                .items(this.toGetItemSimpleDtos(items))
                .build();
    }

    List<GetItemAllByCategoryDto.Response.GetItemSimpleDto> toGetItemSimpleDtos(List<Item> items);

    @Mapping(target = "shopId", source = "item.shop.id")
    @Mapping(target = "shopName", source = "item.shop.name")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "possibleSendType", source = "item.itemSendTypes")
    @Mapping(target = "categoryId", source = "item.category.id")
    @Mapping(target = "createdAt", source = "item.createdAt")
    @Mapping(target = "modifiedAt", source = "item.modifiedAt")
    GetItemAllByCategoryDto.Response.GetItemSimpleDto toGetItemSimpleDto(Item item);

    default Set<SendType> toSendTypes(List<ItemSendType> itemSendTypes) {
        Set<SendType> sendTypes = new HashSet<>();

        for (ItemSendType itemSendType : itemSendTypes) {
            sendTypes.add(itemSendType.getSendType());
        }

        return sendTypes;
    }
}
