package com.tmax.cm.superstore.cart.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.service.CartService;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {
    
    private final CartService cartService;

    @PostMapping("/cartItem")
    public ResponseDto<Void> postCreateCartItem(
            @Valid @RequestBody PostCartItemDto.Request request) {

        this.cartService.createCartItem(request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_CREATE, null);
    }
}
