package com.tmax.cm.superstore.item.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.dto.mapper.PostItemDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
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

    private final PostItemDtoMapper postItemDtoMapper;

    @Transactional
    public Item createItem(PostItemDto.Request postItemDto) {
        Item item = this.postItemDtoMapper.toItem(postItemDto);

        this.itemRepository.save(item);

        return item;
    }
}
