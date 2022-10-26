package com.tmax.cm.superstore.cart.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.tmax.cm.superstore.user.entities.User;

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
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostCartItemDto.Request request) {

        this.cartItemService.create(user, request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_CREATE, null);
    }

    @PostMapping("/cartReservationItem")
    public ResponseDto<Void> postCreateCartReservationItem(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostCartReservationItemDto.Request request) {

        this.cartItemService.createCartReservationItem(user, request);

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_CREATE, null);
    }

    @GetMapping
    public ResponseDto<GetCartDto.Response> getCart(@AuthenticationPrincipal User user) {

        List<CartItem> shippingVisitCartItems = this.cartService.readCart(user, CartType.SHIPPING_VISIT) != null
                ? this.cartService.readCart(user, CartType.SHIPPING_VISIT).getCartItems()
                : null;

        List<CartItem> deliveryPickupCartItems = this.cartService.readCart(user, CartType.DELIVERY_PICKUP) != null
                ? this.cartService.readCart(user, CartType.DELIVERY_PICKUP).getCartItems()
                : null;

        List<CartItem> reservationCartItems = this.cartService.readCart(user, CartType.RESERVATION) != null
                ? this.cartService.readCart(user, CartType.RESERVATION).getCartItems()
                : null;

        GetCartDto.Response response = this.getCartDtoMapper.toResponse(shippingVisitCartItems, deliveryPickupCartItems,
                reservationCartItems);

        response.getShippingVisit().forEach((element) -> element.calculate());
        response.getDeliveryPickup().forEach((element) -> element.calculate());
        response.getReservation().forEach((element) -> element.calculate());

        return new ResponseDto<>(ResponseCode.CART_READ, response);
    }

    @GetMapping("/cartItem/{cartItemId}")
    public ResponseDto<GetCartItemDto.Response> getCartItem(
            @AuthenticationPrincipal User user,
            @PathVariable UUID cartItemId) {

        CartItem cartItem = this.cartItemService.read(user, cartItemId);

        GetCartItemDto.Response response = this.getCartItemDtoMapper.toResponse(cartItem);
        response.calculate();

        return new ResponseDto<>(ResponseCode.CART_ITEM_READ, response);
    }

    @GetMapping("/cartReservationItem/{cartItemId}")
    public ResponseDto<GetCartReservationItemDto.Response> getCartReservationItem(@AuthenticationPrincipal User user,
            @PathVariable UUID cartItemId) {

        CartItem cartItem = this.cartItemService.read(user, cartItemId);

        GetCartReservationItemDto.Response response = this.getCartReservationItemDtoMapper.toResponse(cartItem);
        response.calculate();

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_READ, response);
    }

    @DeleteMapping("/cartItem")
    public ResponseDto<Void> deleteCartItems(@AuthenticationPrincipal User user,
            @Valid @RequestBody DeleteCartItemsHttpDto.Request request) {

        this.cartItemService.delete(user, request);

        return new ResponseDto<>(ResponseCode.CART_ITEMS_DELETE, null);
    }

    @PutMapping("/cartItem/{cartItemId}")
    public ResponseDto<Void> putCartItem(@AuthenticationPrincipal User user, @PathVariable UUID cartItemId,
            @Valid @RequestBody PutCartItemDto.Request request) {

        this.cartItemService.update(user, cartItemId, request);

        return new ResponseDto<>(ResponseCode.CART_ITEM_UPDATE, null);
    }

    @PutMapping("/cartReservationItem/{cartItemId}")
    public ResponseDto<Void> putCartReservationItem(@AuthenticationPrincipal User user, @PathVariable UUID cartItemId,
            @Valid @RequestBody PutCartReservationItemDto.Request request) {

        this.cartItemService.updateCartReservationItem(user, cartItemId, request);

        return new ResponseDto<>(ResponseCode.CART_RESERVATION_ITEM_UPDATE, null);
    }
}
