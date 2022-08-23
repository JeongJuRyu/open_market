package com.tmax.cm.superstore.cart.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.dto.DeleteCartItemsDto;
import com.tmax.cm.superstore.cart.dto.GetCartDto;
import com.tmax.cm.superstore.cart.dto.GetCartItemDto;
import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.dto.PutCartItemDto;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartDtoMapper;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartItemDtoMapper;
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
    private final GetCartItemDtoMapper getCartItemDtoMapper;

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

    @GetMapping("/cartItem/{cartItemId}")
    public ResponseDto<GetCartItemDto.Response> getCartItem(@PathVariable UUID cartItemId) {

        CartItem cartItem = this.cartService.readCartItem(cartItemId);

        return new ResponseDto<>(ResponseCode.CART_ITEM_READ, this.getCartItemDtoMapper.toResponse(cartItem));
    }

    @DeleteMapping("/cartItem")
    public ResponseDto<Void> deleteCartItems(@Valid @RequestBody DeleteCartItemsDto.Request request) {

        this.cartService.deleteCartItems(request);

        return new ResponseDto<>(ResponseCode.CART_ITEMS_DELETE, null);
    }

    @PutMapping("/cartItem/{cartItemId}")
    public ResponseDto<Void> putCartItem(@PathVariable UUID cartItemId,
            @Valid @RequestBody PutCartItemDto.Request request) {

        this.cartService.updateCartItem(cartItemId, request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_UPDATE, null);
    }
}
