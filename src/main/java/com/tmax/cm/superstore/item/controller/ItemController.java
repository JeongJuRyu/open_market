package com.tmax.cm.superstore.item.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.dto.GetItemAllDto;
import com.tmax.cm.superstore.item.dto.GetItemDto;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.GetItemDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.PostItemDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;

    private final PostItemDtoMapper postItemDtoMapper;
    private final GetItemDtoMapper getItemDtoMapper;
    private final GetItemAllDtoMapper getItemAllDtoMapper;

    @PostMapping
    public ResponseDto<PostItemDto.Response> postCreateItem(
            @Valid @RequestBody PostItemDto.Request request) {

        Item item = this.itemService.createItem(request);

        return new ResponseDto<>(ResponseCode.ITEM_CREATE, this.postItemDtoMapper.toResponse(item));
    }

    @GetMapping("/{itemId}")
    public ResponseDto<GetItemDto.Response> getItem(@PathVariable UUID itemId) {

        Item item = this.itemService.readItem(itemId);

        return new ResponseDto<>(ResponseCode.ITEM_READ, this.getItemDtoMapper.toResponse(item));
    }

    @GetMapping
    public ResponseDto<GetItemAllDto.Response> getItemAll() {

        List<Item> items = this.itemService.readItems();

        return new ResponseDto<>(ResponseCode.ITEM_READ, this.getItemAllDtoMapper.toResponse(items));
    }
}