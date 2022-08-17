package com.tmax.cm.superstore.item.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseDto<Void> postCreateItem(
            @Valid @RequestBody PostItemDto.Request request) {

        this.itemService.createItem(request);

        return new ResponseDto<>(ResponseCode.ITEM_CREATE, null);
    }
}
