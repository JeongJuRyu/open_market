package com.tmax.cm.superstore.purchaseOrder.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartItemService;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.purchaseOrder.controller.dto.PostPurchaseOrderBuyNowDto;
import com.tmax.cm.superstore.purchaseOrder.controller.dto.PostPurchaseOrderCartDto;
import com.tmax.cm.superstore.purchaseOrder.controller.dto.mapper.PostPurchaseOrderBuyNowDtoMapper;
import com.tmax.cm.superstore.purchaseOrder.controller.dto.mapper.PostPurchaseOrderCartDtoMapper;
import com.tmax.cm.superstore.purchaseOrder.service.PurchaseOrderService;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/purchaseOrder")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;
    private final CartItemService cartItemService;

    private final PostPurchaseOrderCartDtoMapper postPurchaseOrderCartDtoMapper;
    private final PostPurchaseOrderBuyNowDtoMapper postPurchaseOrderBuyNowDtoMapper;

    @PostMapping("/cart")
    public ResponseDto<PostPurchaseOrderCartDto.Response> postPurchaseOrderCart(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostPurchaseOrderCartDto.Request request) {

        List<CartItem> cartItems = this.cartItemService.read(user, request.getCartItemIds());

        PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.read(cartItems);

        return new ResponseDto<>(ResponseCode.PURCHASE_ORDER_CART_CREATE,
                this.postPurchaseOrderCartDtoMapper.toResponse(purchaseOrderDto));
    }

    @PostMapping("/buyNow")
    public ResponseDto<PostPurchaseOrderBuyNowDto.Response> postPurchaseOrderBuyNow(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostPurchaseOrderBuyNowDto.Request request) {

        CartItem cartItem = this.cartItemService.create(user, request);

        PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.read(cartItem);

        return new ResponseDto<>(ResponseCode.PURCHASE_ORDER_BUY_NOW_CREATE,
                this.postPurchaseOrderBuyNowDtoMapper.toResponse(purchaseOrderDto));
    }
}