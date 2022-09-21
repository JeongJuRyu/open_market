package com.tmax.cm.superstore.item.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.tmax.cm.superstore.item.dto.FileInfo;
import com.tmax.cm.superstore.item.entity.*;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.category.service.CategoryService;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.shop.entity.Shop;
import com.tmax.cm.superstore.shop.repository.ShopRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Transactional
    public Item createItem(PostItemDto.Request postItemDto, List<MultipartFile> attachment) {

        Shop shop = Shop.builder()
                .name(postItemDto.getShopName())
                .build();
        
        this.shopRepository.save(shop);

        Category category = this.categoryService.getCategoryEntity(postItemDto.getCategoryId());

        Item item = Item.builder()
                .shop(shop)
                .name(postItemDto.getName())
                .price(postItemDto.getPrice())
                .itemImages(new ArrayList<>())
                .optionGroups(new ArrayList<>())
                .itemSendTypes(new ArrayList<>())
                .category(category)
                .build();

        List<FileInfo> fileInfos = imageService.uploadImages(attachment);

        fileInfos.stream().map(ItemImage::create).forEach(item::addItemImage);

        for (SendType sendType : postItemDto.getPossibleSendType()) {
            ItemSendType itemSendType = ItemSendType.builder()
                    .sendType(sendType)
                    .item(item)
                    .build();

            item.getItemSendTypes().add(itemSendType);
        }

        for (PostItemDto.Request.PostOptionGroupDto postOptionGroupDto : postItemDto.getOptionGroups()) {
            OptionGroup optionGroup = OptionGroup.builder()
                    .name(postOptionGroupDto.getName())
                    .isNecessary(postOptionGroupDto.getIsNecessary())
                    .options(new ArrayList<>())
                    .item(item)
                    .build();

            for (PostItemDto.Request.PostOptionGroupDto.PostOptionDto postOptionDto : postOptionGroupDto.getOptions()) {
                Option option = Option.builder()
                        .name(postOptionDto.getName())
                        .price(postOptionDto.getPrice())
                        .description(postOptionDto.getDescription())
                        .optionGroup(optionGroup)
                        .build();

                optionGroup.getOptions().add(option);
            }

            item.getOptionGroups().add(optionGroup);
        }

        this.itemRepository.save(item);

        return item;
    }

    @Transactional
    public Item readItem(UUID itemId) {

        Item item = this.itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);

        return item;
    }

    @Transactional
    public List<Item> readItems() {

        List<Item> items = this.itemRepository.findAll();

        return items;
    }

    @Transactional
    public List<Item> readItemsByCategory(Long categoryId) {
        CategoryDto category = categoryService.getCategory(categoryId);

        List<CategoryDto> subCategoryList = category.getSubCategories();

        List<Item> itemList = new ArrayList<>();

        if(subCategoryList == null){
            return itemRepository.findByCategoryId(categoryId);
        } else{
            for (CategoryDto categoryDto:subCategoryList
            ) {
                itemList.addAll(readItemsByCategory(categoryDto.getCategoryId()));
            }
            return itemList;
        }
    }
}
