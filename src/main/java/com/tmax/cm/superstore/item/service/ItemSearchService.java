package com.tmax.cm.superstore.item.service;

import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.dto.GetItemAllDto;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllByCategoryDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemSearchService {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final GetItemAllByCategoryDtoMapper getItemAllByCategoryDtoMapper;
    private final GetItemAllDtoMapper getItemAllDtoMapper;
    @Transactional
    public GetItemAllByCategoryDto.Response searchItemByName(String name){
        List<Item> items = itemRepository.findByNameContaining(name);

        if (items.isEmpty()){
            throw new ItemNotFoundException();
        }
        else{
            List<Double> avgStars = itemService.getAvgStars(items);
            List<Integer> reviewCounts = itemService.getReviewCounts(items);

            return getItemAllByCategoryDtoMapper.toResponse(items, avgStars, reviewCounts);
        }
    }

    @Transactional
    public GetItemAllDto.Response searchItemByFilter(String name,  Long categoryId, List<ItemState> itemState){
        List<Item> items = itemRepository.findByKeyword(name, categoryId, itemState);
        if (items.isEmpty()){
            throw new ItemNotFoundException();
        }
        else{
            return getItemAllDtoMapper.toResponse(items);
        }
    }
}
