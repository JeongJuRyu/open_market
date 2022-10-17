package com.tmax.cm.superstore.order.controller;

import java.util.List;

import javax.transaction.Transactional;
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
import com.tmax.cm.superstore.order.controller.dto.PostOrderDto;
import com.tmax.cm.superstore.order.service.OrderService;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.payment.service.PaymentService;
import com.tmax.cm.superstore.purchaseOrder.service.PurchaseOrderService;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.service.ShippingService;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PurchaseOrderService purchaseOrderService;
    private final CartItemService cartItemService;
    private final ShippingService shippingService;

    @PostMapping
    public ResponseDto<Void> postCreateCartItem(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostOrderDto.Request request) {

        this.createOrderInTransaction(user, request);

        return new ResponseDto<>(ResponseCode.ORDER_CREATE, null);
    }

    @Transactional
    private void createOrderInTransaction(User user, PostOrderDto.Request request) {
        List<CartItem> cartItems = this.cartItemService.read(request.getCartItemIds());
        Payment payment = this.paymentService.create(request);
        PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.read(cartItems);

        Shipping shipping = this.shippingService.create("null", "null", "null", "null");

        this.orderService.create(user, payment, purchaseOrderDto, shipping);

        this.cartItemService.delete(cartItems);
    }
}
