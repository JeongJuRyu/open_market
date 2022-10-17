package com.tmax.cm.superstore.item.service;

import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllByCategoryDtoMapper;
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

    @Transactional
    public GetItemAllByCategoryDto.Response searchItemByKeyword(String keyword, Long categoryId){
        List<Item> items = itemRepository.findByKeyword(keyword, categoryId);

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

}