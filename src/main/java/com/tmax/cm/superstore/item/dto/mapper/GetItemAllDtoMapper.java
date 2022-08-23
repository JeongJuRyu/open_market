package com.tmax.cm.superstore.item.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.dto.GetItemAllDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;

@Mapper(config = CommonMapperConfig.class)
public interface GetItemAllDtoMapper {

    default GetItemAllDto.Response toResponse(List<Item> items) {
        return GetItemAllDto.Response.builder()
                .items(this.toGetItemDtos(items))
                .build();
    }

    List<GetItemAllDto.Response.GetItemDto> toGetItemDtos(List<Item> items);

    default GetItemAllDto.Response.GetItemDto toResponse(Item item) {
        List<GetItemAllDto.Response.GetItemDto.GetOptionGroupDto> necessaryOptionGroups = new ArrayList<>();
        List<GetItemAllDto.Response.GetItemDto.GetOptionGroupDto> optionalOptionGroups = new ArrayList<>();

        for (OptionGroup optionGroup : item.getOptionGroups()) {
            if (optionGroup.getIsNecessary()) {
                necessaryOptionGroups.add(this.toGetOptionDto(optionGroup));
            } else {
                optionalOptionGroups.add(this.toGetOptionDto(optionGroup));
            }
        }

        return this.toGetItemDto(item, necessaryOptionGroups, optionalOptionGroups);
    }

    @Mapping(target = "shopId", source = "item.shop.id")
    @Mapping(target = "shopName", source = "item.shop.name")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "possibleSendType", source = "item.itemSendTypes")
    GetItemAllDto.Response.GetItemDto toGetItemDto(Item item,
            List<GetItemAllDto.Response.GetItemDto.GetOptionGroupDto> necessaryOptionGroups,
            List<GetItemAllDto.Response.GetItemDto.GetOptionGroupDto> optionalOptionGroups);

    default Set<SendType> toSendTypes(List<ItemSendType> itemSendTypes) {
        Set<SendType> sendTypes = new HashSet<>();

        for (ItemSendType itemSendType : itemSendTypes) {
            sendTypes.add(itemSendType.getSendType());
        }

        return sendTypes;
    }

    @Mapping(target = "optionGroupId", source = "id")
    @Mapping(target = "optionGroupName", source = "name")
    GetItemAllDto.Response.GetItemDto.GetOptionGroupDto toGetOptionDto(OptionGroup optionGroup);

    @Mapping(target = "optionId", source = "id")
    @Mapping(target = "optionName", source = "name")
    @Mapping(target = "optionPrice", source = "price")
    @Mapping(target = "optionDescription", source = "description")
    GetItemAllDto.Response.GetItemDto.GetOptionGroupDto.GetOptionDto toGetOptionDto(Option option);
}
