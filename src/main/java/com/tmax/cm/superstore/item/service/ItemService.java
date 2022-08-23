package com.tmax.cm.superstore.item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.shop.entity.Shop;
import com.tmax.cm.superstore.shop.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Item createItem(PostItemDto.Request postItemDto) {

        Shop shop = Shop.builder()
                .name(postItemDto.getShopName())
                .build();
        
        this.shopRepository.save(shop);

        Item item = Item.builder()
                .shop(shop)
                .name(postItemDto.getName())
                .price(postItemDto.getPrice())
                .optionGroups(new ArrayList<>())
                .itemSendTypes(new ArrayList<>())
                .build();

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
}
