package com.tmax.cm.superstore.item.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.dto.mapper.PostItemDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.item.repository.OptionGroupRepository;
import com.tmax.cm.superstore.item.repository.OptionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final OptionGroupRepository optionGroupRepository;
    private final OptionRepository optionRepository;

    @Transactional
    public Item createItem(PostItemDto.Request postItemDto) {

        Item item = Item.builder().name(postItemDto.getName())
                .price(postItemDto.getPrice())
                .optionGroups(new ArrayList<>())
                .build();

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
                        .optionGroup(optionGroup)
                        .build();

                optionGroup.getOptions().add(option);
            }

            item.getOptionGroups().add(optionGroup);
        }

        this.itemRepository.save(item);

        return item;
    }
}
