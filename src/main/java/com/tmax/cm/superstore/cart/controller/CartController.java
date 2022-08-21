package com.tmax.cm.superstore.cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.dto.GetCartDto;
import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartDtoMapper;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartService;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;

    private final GetCartDtoMapper getCartDtoMapper;

    @PostMapping("/cartItem")
    public ResponseDto<Void> postCreateCartItem(
            @Valid @RequestBody PostCartItemDto.Request request) {

        this.cartService.createCartItem(request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_CREATE, null);
    }

    @GetMapping
    public ResponseDto<GetCartDto.Response> getCart() {

        List<CartItem> shippingVisitCartItems = this.cartService.readCart(CartType.SHIPPING_VISIT) != null
                ? this.cartService.readCart(CartType.SHIPPING_VISIT).getCartItems()
                : null;

        List<CartItem> deliveryPickupCartItems = this.cartService.readCart(CartType.DELIVERY_PICKUP) != null
                ? this.cartService.readCart(CartType.DELIVERY_PICKUP).getCartItems()
                : null;

        List<CartItem> reservationCartItems = this.cartService.readCart(CartType.RESERVATION) != null
                ? this.cartService.readCart(CartType.RESERVATION).getCartItems()
                : null;

        GetCartDto.Response response = this.getCartDtoMapper.toResponse(shippingVisitCartItems, deliveryPickupCartItems,
                reservationCartItems);

        return new ResponseDto<>(ResponseCode.CART_READ, response);
    }
}
