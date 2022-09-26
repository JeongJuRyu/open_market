package com.tmax.cm.superstore.item.dto.mapper;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.config.CommonMapperConfig;

import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemImage;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(config= CommonMapperConfig.class)
public interface GetItemAllByCategoryDtoMapper {

    default GetItemAllByCategoryDto.Response toResponse(List<Item> items, List<Double> avg, List<Integer> count) {
        return GetItemAllByCategoryDto.Response.builder()
                .items(this.toGetItemSimpleDtos(items))
                .simpleReview(this.toGetSimpleReviewDtos(avg, count))
                .build();
    }

    default GetItemAllByCategoryDto.Response.GetItemSimpleDto toResponse(Item item) {

        List<GetItemAllByCategoryDto.Response.GetItemSimpleDto.GetItemImageDto> images = new ArrayList<>();

        if(!item.getItemImages().isEmpty()){
            for (ItemImage itemImage : item.getItemImages()){
                images.add(this.toGetItemImage(itemImage));
            };
        }

        return this.toGetItemSimpleDto(item, images);

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
    GetItemAllByCategoryDto.Response.GetItemSimpleDto toGetItemSimpleDto(Item item, List<GetItemAllByCategoryDto.Response.GetItemSimpleDto.GetItemImageDto> images);

    default Set<SendType> toSendTypes(List<ItemSendType> itemSendTypes) {
        Set<SendType> sendTypes = new HashSet<>();

        for (ItemSendType itemSendType : itemSendTypes) {
            sendTypes.add(itemSendType.getSendType());
        }

        return sendTypes;
    }

    @Mapping(target = "fileId", source = "fileId")
    GetItemAllByCategoryDto.Response.GetItemSimpleDto.GetItemImageDto toGetItemImage(ItemImage itemImage);

//    @Mapping(target = ".", source = "avgScore")
//    @Mapping(target = ".", source = "reviewCount")
//    List<GetItemAllByCategoryDto.Response.GetSimpleReviewDto> toGetSimpleReviewDtos(List<Float> avgScore, List<Integer> reviewCount);
//
//    @Mapping(target = "avgScore", source = "avgScore")
//    @Mapping(target = "reviewCount", source = "reviewCount")
//    GetItemAllByCategoryDto.Response.GetSimpleReviewDto toGetSimpleReviewDto(Float avgScore, Integer reviewCount);


    default List<GetItemAllByCategoryDto.Response.GetSimpleReviewDto> toGetSimpleReviewDtos(List<Double> avgScore, List<Integer> reviewCount) {
        List<GetItemAllByCategoryDto.Response.GetSimpleReviewDto> dtos = new ArrayList<>();

        for (int i = 0; i < avgScore.size(); i++) {
            dtos.add(this.toGetSimpleReviewDto(avgScore.get(i), reviewCount.get(i)));
        }

        return dtos;
    }

     GetItemAllByCategoryDto.Response.GetSimpleReviewDto toGetSimpleReviewDto(Double avgScore, Integer reviewCount);
}
