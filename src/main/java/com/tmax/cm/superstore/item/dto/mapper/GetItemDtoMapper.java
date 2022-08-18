package com.tmax.cm.superstore.item.dto.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.dto.GetItemDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;

@Mapper(config = CommonMapperConfig.class)
public interface GetItemDtoMapper {

    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "shopName", source = "shop.name")
    @Mapping(target = "itemId", source = "id")
    @Mapping(target = "itemName", source = "name")
    @Mapping(target = "itemPrice", source = "price")
    @Mapping(target = "possibleSendType", source = "itemSendTypes")
    GetItemDto.Response toResponse(Item item);

    default Set<SendType> toSendTypes(List<ItemSendType> itemSendTypes) {
        Set<SendType> sendTypes = new HashSet<>();

        for (ItemSendType itemSendType : itemSendTypes) {
            sendTypes.add(itemSendType.getSendType());
        }

        return sendTypes;
    }

    @Mapping(target = "optionGroupId", source = "id")
    @Mapping(target = "optionGroupName", source = "name")
    GetItemDto.Response.GetOptionGroupDto toGetOptionDto(OptionGroup optionGroup);

    @Mapping(target = "optionId", source = "id")
    GetItemDto.Response.GetOptionGroupDto.GetOptionDto toGetOptionDto(Option option);
}
