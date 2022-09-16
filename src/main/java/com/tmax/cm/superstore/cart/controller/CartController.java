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

import com.tmax.cm.superstore.cart.dto.DeleteCartItemsHttpDto;
import com.tmax.cm.superstore.cart.dto.GetCartDto;
import com.tmax.cm.superstore.cart.dto.GetCartItemDto;
import com.tmax.cm.superstore.cart.dto.GetCartReservationItemDto;
import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.dto.PostCartReservationItemDto;
import com.tmax.cm.superstore.cart.dto.PutCartItemDto;
import com.tmax.cm.superstore.cart.dto.PutCartReservationItemDto;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartDtoMapper;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartItemDtoMapper;
import com.tmax.cm.superstore.cart.dto.mapper.GetCartReservationItemDtoMapper;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartItemService;
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
    private final CartItemService cartItemService;

    private final GetCartDtoMapper getCartDtoMapper;
    private final GetCartItemDtoMapper getCartItemDtoMapper;
    private final GetCartReservationItemDtoMapper getCartReservationItemDtoMapper;

    @PostMapping("/cartItem")
    public ResponseDto<Void> postCreateCartItem(
            @Valid @RequestBody PostCartItemDto.Request request) {

        this.cartItemService.create(request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_CREATE, null);
    }

    @PostMapping("/cartReservationItem")
    public ResponseDto<Void> postCreateCartReservationItem(
            @Valid @RequestBody PostCartReservationItemDto.Request request) {

        this.cartItemService.createCartReservationItem(request);

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_CREATE, null);
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

        response.getShippingVisit().forEach((element) -> element.calculate());
        response.getDeliveryPickup().forEach((element) -> element.calculate());
        response.getReservation().forEach((element) -> element.calculate());

        return new ResponseDto<>(ResponseCode.CART_READ, response);
    }

    @GetMapping("/cartItem/{cartItemId}")
    public ResponseDto<GetCartItemDto.Response> getCartItem(@PathVariable UUID cartItemId) {

        CartItem cartItem = this.cartItemService.read(cartItemId);

        GetCartItemDto.Response response = this.getCartItemDtoMapper.toResponse(cartItem);
        response.calculate();

        return new ResponseDto<>(ResponseCode.CART_ITEM_READ, response);
    }

    @GetMapping("/cartReservationItem/{cartItemId}")
    public ResponseDto<GetCartReservationItemDto.Response> getCartReservationItem(@PathVariable UUID cartItemId) {

        CartItem cartItem = this.cartItemService.read(cartItemId);

        GetCartReservationItemDto.Response response = this.getCartReservationItemDtoMapper.toResponse(cartItem);
        response.calculate();

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_READ, response);
    }

    @DeleteMapping("/cartItem")
    public ResponseDto<Void> deleteCartItems(@Valid @RequestBody DeleteCartItemsHttpDto.Request request) {

        this.cartItemService.delete(request);

        return new ResponseDto<>(ResponseCode.CART_ITEMS_DELETE, null);
    }

    @PutMapping("/cartItem/{cartItemId}")
    public ResponseDto<Void> putCartItem(@PathVariable UUID cartItemId,
            @Valid @RequestBody PutCartItemDto.Request request) {

        this.cartItemService.update(cartItemId, request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_UPDATE, null);
    }

    @PutMapping("/cartReservationItem/{cartItemId}")
    public ResponseDto<Void> putCartReservationItem(@PathVariable UUID cartItemId,
            @Valid @RequestBody PutCartReservationItemDto.Request request) {

        this.cartItemService.updateCartReservationItem(cartItemId, request);

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_UPDATE, null);
    }
}
