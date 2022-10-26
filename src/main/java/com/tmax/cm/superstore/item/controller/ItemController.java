package com.tmax.cm.superstore.item.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.tmax.cm.superstore.category.service.CategoryService;
import com.tmax.cm.superstore.item.code.ItemState;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.util.TransactionHandler;
import com.tmax.cm.superstore.item.dto.GetItemAllByCategoryDto;
import com.tmax.cm.superstore.item.dto.GetItemAllDto;
import com.tmax.cm.superstore.item.dto.GetItemDto;
import com.tmax.cm.superstore.item.dto.PostItemDto;
import com.tmax.cm.superstore.item.dto.UpdateItemDto;
import com.tmax.cm.superstore.item.dto.mapper.GetItemAllDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.GetItemDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.PostItemDtoMapper;
import com.tmax.cm.superstore.item.dto.mapper.UpdateItemDtoMapper;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.service.ItemSearchService;
import com.tmax.cm.superstore.item.service.ItemService;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.service.SellerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemSearchService itemSearchService;
    private final SellerService sellerService;
    private final CategoryService categoryService;

    private final TransactionHandler transactionHandler;

    private final PostItemDtoMapper postItemDtoMapper;
    private final GetItemDtoMapper getItemDtoMapper;
    private final GetItemAllDtoMapper getItemAllDtoMapper;
    private final UpdateItemDtoMapper updateItemDtoMapper;

    @PostMapping("/seller/{sellerId}/create")
    public ResponseDto<PostItemDto.Response> postCreateItem(
            @PathVariable UUID sellerId,
            @Valid @RequestPart("request") PostItemDto.Request request,
            @RequestPart(value = "attachment", required = false) List<MultipartFile> attachment) {

        Item item = this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            return this.itemService.createItem(seller, request, attachment);
        });

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
    public void deleteItem(@PathVariable UUID itemId) {
        this.itemService.deleteItem(itemId);
    }

    @PatchMapping("/update/{itemId}")
    public ResponseDto<UpdateItemDto.Response> updateItem(@RequestPart("request") UpdateItemDto.Request request,
            @PathVariable UUID itemId) {
        Item item = this.itemService.updateItem(itemId, request);

        return new ResponseDto<>(ResponseCode.ITEM_UPDATE, this.updateItemDtoMapper.toResponse(item));
    }

    @GetMapping("/simpleItems")
    public ResponseDto<GetItemAllByCategoryDto.Response> getItemByCategory(
            @RequestParam("categoryId") Long categoryId) {
        return new ResponseDto<>(ResponseCode.ITEM_READ_ALL, itemService.readSimpleItem(categoryId));
    }

    @GetMapping("/count")
    public ResponseDto<Long> getItemCount(){
        return new ResponseDto<>(ResponseCode.ITEM_READ_ALL, itemService.getItemCount());
    }

    @GetMapping("/search/item")
    public ResponseDto<GetItemAllDto.Response> searchItemByFilter(@RequestParam(value = "name", required = false)String name,
                                                                  @RequestParam(value = "categoryId") Long categoryId,
                                                                  @RequestParam(value = "itemState", required = false) List<ItemState> itemState) {
        Long parentCategoryId = categoryService.getParentCategoryId(categoryId);
        return new ResponseDto<>(ResponseCode.ITEM_READ_ALL, itemSearchService.searchItemByFilter(name, parentCategoryId, itemState));
    }

    @GetMapping("/search/keyword")
    public ResponseDto<GetItemAllByCategoryDto.Response> searchItemByName(@RequestParam String name){
        return new ResponseDto<>(ResponseCode.ITEM_READ_ALL, itemSearchService.searchItemByName(name));
    }

}
