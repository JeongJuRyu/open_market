package com.tmax.cm.superstore.item.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.dto.mapper.*;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.tmax.cm.superstore.item.dto.*;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.service.ItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;

    private final PostItemDtoMapper postItemDtoMapper;
    private final GetItemDtoMapper getItemDtoMapper;
    private final GetItemAllDtoMapper getItemAllDtoMapper;
    private final UpdateItemDtoMapper updateItemDtoMapper;

    @PostMapping("/create")
    public ResponseDto<PostItemDto.Response> postCreateItem(
            @Valid @RequestPart("request") PostItemDto.Request request, @RequestPart(value = "attachment", required = false) List<MultipartFile> attachment){

        Item item = this.itemService.createItem(request, attachment);

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

    @DeleteMapping("/delete/{itemId}")
    public void deleteItem(@PathVariable UUID itemId){
        this.itemService.deleteItem(itemId);
    }

    @PatchMapping("/update/{itemId}")
    public ResponseDto<UpdateItemDto.Response> updateItem(@RequestPart("request") UpdateItemDto.Request request, @PathVariable UUID itemId){
        Item item = this.itemService.updateItem(itemId, request);

        return new ResponseDto<>(ResponseCode.ITEM_UPDATE, this.updateItemDtoMapper.toResponse(item));
    }

    @GetMapping("/simpleItems")
    public ResponseDto<GetItemAllByCategoryDto.Response> getItemByCategory(@RequestParam("categoryId") Long categoryId) {
        return new ResponseDto<>(ResponseCode.ITEM_READ_ALL, itemService.readSimpleItem(categoryId));
    }
}
