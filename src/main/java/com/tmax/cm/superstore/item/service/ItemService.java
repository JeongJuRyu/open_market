package com.tmax.cm.superstore.item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import net.bytebuddy.description.field.FieldDescription;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.category.error.exception.CategoryNotFoundException;
import com.tmax.cm.superstore.category.repository.CategoryRepository;
import com.tmax.cm.superstore.category.service.CategoryService;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.dto.FileInfo;
import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.dto.UpdateItemDto;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllByCategoryDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemImage;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.mypage.service.ReviewService;
import com.tmax.cm.superstore.seller.entity.Seller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;
    private final ImageService imageService;
    private final ReviewService reviewService;

    private final GetItemAllByCategoryDtoMapper getItemAllByCategoryDtoMapper;

    @Transactional
    public Item createItem(Seller seller, PostItemDto.Request postItemDto, List<MultipartFile> attachment) {

        Category category = this.categoryService.getCategoryEntity(postItemDto.getCategoryId());

        Item item = Item.builder()
                .seller(seller)
                .name(postItemDto.getName())
                .price(postItemDto.getPrice())
                .itemImages(new ArrayList<>())
                .itemState(postItemDto.getItemState())
                .optionGroups(new ArrayList<>())
                .itemSendTypes(new ArrayList<>())
                .shippingCharge(postItemDto.getShippingCharge())
                .shippingChargeType(postItemDto.getShippingChargeType())
                .returnAddress(postItemDto.getReturnAddress())
                .returnCharge(postItemDto.getReturnCharge())
                .description(postItemDto.getDescription())
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

    public List<Item> readItemsByCategory(Long categoryId) {
        CategoryDto category = categoryService.getCategory(categoryId);

        List<CategoryDto> subCategoryList = category.getSubCategories();

        List<Item> itemList = new ArrayList<>();

        if (subCategoryList == null) {
            return itemRepository.findByCategoryId(categoryId);
        } else {
            for (CategoryDto categoryDto : subCategoryList) {
                itemList.addAll(readItemsByCategory(categoryDto.getCategoryId()));
            }
            return itemList;
        }
    }

    @Transactional
    public GetItemAllByCategoryDto.Response readSimpleItem(Long categoryId) {
        List<Item> items = readItemsByCategory(categoryId);

        List<Double> avgStars = getAvgStars(items);
        List<Integer> reviewCounts = getReviewCounts(items);

        return getItemAllByCategoryDtoMapper.toResponse(items, avgStars, reviewCounts);
    }

    public List<Double> getAvgStars(List<Item> items) {
        List<Double> avgStars = new ArrayList<>();

        for (Item item : items) {
            if (item.getReviews().isEmpty()) {
                avgStars.add(0.0);
            } else {
                avgStars.add(reviewService.getAvgStarRating(item.getId()));
            }
        }

        return avgStars;
    }

    public List<Integer> getReviewCounts(List<Item> items) {
        List<Integer> reviewCounts = new ArrayList<>();

        for (Item item : items) {
            if (item.getReviews().isEmpty()) {
                reviewCounts.add(0);
            } else {
                reviewCounts.add(item.getReviews().size());
            }
        }

        return reviewCounts;
    }

    @Transactional
    public Item updateItem(UUID itemId, UpdateItemDto.Request updateItemDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(ItemNotFoundException::new);
        Category category = categoryRepository.findById(updateItemDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        item.getItemSendTypes().clear();

        for (SendType sendType : updateItemDto.getPossibleSendType()) {
            ItemSendType itemSendType = ItemSendType.builder()
                    .sendType(sendType)
                    .item(item)
                    .build();

            item.getItemSendTypes().add(itemSendType);
        }

        item.getOptionGroups().clear();

        for (UpdateItemDto.Request.PostOptionGroupDto postOptionGroupDto : updateItemDto.getOptionGroups()) {
            OptionGroup optionGroup = OptionGroup.builder()
                    .name(postOptionGroupDto.getName())
                    .isNecessary(postOptionGroupDto.getIsNecessary())
                    .options(new ArrayList<>())
                    .item(item)
                    .build();

            for (UpdateItemDto.Request.PostOptionGroupDto.PostOptionDto postOptionDto : postOptionGroupDto
                    .getOptions()) {
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

        item.updateItem(category, updateItemDto.getName(), updateItemDto.getPrice(), updateItemDto.getItemState()
                            ,updateItemDto.getShippingChargeType(), updateItemDto.getShippingCharge()
                            ,updateItemDto.getDescription(), updateItemDto.getReturnAddress(), updateItemDto.getReturnCharge());

        this.itemRepository.save(item);

        return item;
    }

    @Transactional
    public void deleteItem(UUID itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        itemRepository.delete(item);
    }

    @Transactional
    public Long getItemCount() {
        return itemRepository.count();
    }

}
