package com.tmax.cm.superstore.order.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartItemService;
import com.tmax.cm.superstore.code.PaymentType;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.util.TransactionHandler;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionByUserDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionByUserDto;
import com.tmax.cm.superstore.order.controller.dto.PostOrderDto;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionAllByUserDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionByUserDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionByUserDtoMapper;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.order.service.OrderService;
import com.tmax.cm.superstore.order.service.dto.ReadPickupOrderSelectedOptionDto;
import com.tmax.cm.superstore.order.service.dto.ReadShippingOrderSelectedOptionDto;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.payment.service.PaymentService;
import com.tmax.cm.superstore.purchaseOrder.service.PurchaseOrderService;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/order/buyer")
public class OrderBuyerController {

    private final OrderService orderService;
    private final PaymentService paymentService;
    private final PurchaseOrderService purchaseOrderService;
    private final CartItemService cartItemService;

    private final TransactionHandler transactionHandler;

    private final GetPickupOrderSelectedOptionAllByUserDtoMapper getPickupOrderSelectedOptionAllByUserDtoMapper;
    private final GetShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper getShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper;
    private final GetPickupOrderSelectedOptionByUserDtoMapper getPickupOrderSelectedOptionByUserDtoMapper;
    private final GetShippingAndDeliveryOrderSelectedOptionByUserDtoMapper getShippingAndDeliveryOrderSelectedOptionByUserDtoMapper;

    @PostMapping
    public ResponseDto<Void> postCreateOrder(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostOrderDto.Request request) {

        this.transactionHandler.runInSameTransaction(() -> {
            List<CartItem> cartItems = this.cartItemService.read(user, request.getCartItemIds());
            Payment payment = this.paymentService.create(request);
            PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.read(cartItems);

            this.orderService.create(user, payment, purchaseOrderDto, request);

            this.cartItemService.delete(cartItems);
        });

        return new ResponseDto<>(ResponseCode.ORDER_CREATE, null);
    }

    @GetMapping("/visitAndPickup")
    public ResponseDto<GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response> getVisitAndPickupOrderSelectedOptionAllByUser(
            @AuthenticationPrincipal User user, @RequestParam(required = false) PickupType pickupType,
            @RequestParam(required = false) SendType sendType) {
        List<VisitOrder> visitOrders;
        List<PickupOrder> pickupOrders;
        PaymentType paymentType = null;

        if (pickupType == null) {
            visitOrders = this.orderService.readVisitOrders(user);
            pickupOrders = this.orderService.readPickupOrders(user);
        } else {
            visitOrders = this.orderService.readVisitOrdersByPickupType(user, pickupType);
            pickupOrders = this.orderService.readPickupOrdersByPickupType(user, pickupType);
        }

        if (!visitOrders.isEmpty()) {
            paymentType = visitOrders.get(0).getOrder().getPayment().getPaymentType();
        }

        if (!pickupOrders.isEmpty()) {
            paymentType = visitOrders.get(0).getOrder().getPayment().getPaymentType();
        }

        if (sendType == SendType.VISIT) {
            pickupOrders.clear();
        }

        if (sendType == SendType.PICKUP) {
            visitOrders.clear();
        }

        if (sendType == SendType.RESERVATION || sendType == SendType.DELIVERY || sendType == SendType.SHIPPING) {
            pickupOrders.clear();
            visitOrders.clear();
        }

        GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response response = this.getPickupOrderSelectedOptionAllByUserDtoMapper
                .toResponse(visitOrders, pickupOrders, paymentType);

        return new ResponseDto<>(ResponseCode.ORDER_VISIT_AND_PICKUP_READ, response);
    }

    @GetMapping("/visitAndPickup/{selectedOptionId}")
    public ResponseDto<GetVisitAndPickupOrderSelectedOptionByUserDto.Response> getVisitAndPickupOrderSelectedOptionByUser(
            @AuthenticationPrincipal User user, @PathVariable UUID selectedOptionId) {

        ReadPickupOrderSelectedOptionDto dto = this.orderService.readPickupOrderSelectedOption(selectedOptionId,
                user.getId());

        GetVisitAndPickupOrderSelectedOptionByUserDto.Response response = this.getPickupOrderSelectedOptionByUserDtoMapper
                .toResponse(dto);

        return new ResponseDto<>(ResponseCode.ORDER_VISIT_AND_PICKUP_READ, response);
    }

    @GetMapping("/shippingAndDelivery")
    public ResponseDto<GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response> getShippingAndDeliveryOrderSelectedOptionAllByUser(
            @AuthenticationPrincipal User user, @RequestParam(required = false) ShippingType shippingType,
            @RequestParam(required = false) SendType sendType) {

        List<ShippingOrder> shippingOrders;
        List<DeliveryOrder> deliveryOrders;
        PaymentType paymentType = null;

        if (shippingType == null) {
            shippingOrders = this.orderService.readShippingOrders(user);
            deliveryOrders = this.orderService.readDeliveryOrders(user);
        } else {
            shippingOrders = this.orderService.readShippingOrdersByShippingType(user, shippingType);
            deliveryOrders = this.orderService.readDeliveryOrdersByShippingType(user, shippingType);
        }

        if (!shippingOrders.isEmpty()) {
            paymentType = shippingOrders.get(0).getOrder().getPayment().getPaymentType();
        }

        if (!deliveryOrders.isEmpty()) {
            paymentType = deliveryOrders.get(0).getOrder().getPayment().getPaymentType();
        }

        if (sendType == SendType.SHIPPING) {
            deliveryOrders.clear();
        }

        if (sendType == SendType.DELIVERY) {
            shippingOrders.clear();
        }

        if (sendType == SendType.RESERVATION || sendType == SendType.PICKUP || sendType == SendType.VISIT) {
            deliveryOrders.clear();
            shippingOrders.clear();
        }

        GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response response = this.getShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper
                .toResponse(shippingOrders, deliveryOrders, paymentType);

        return new ResponseDto<>(ResponseCode.ORDER_SHIPPING_AND_DELIVERY_READ, response);
    }

    @GetMapping("/shippingAndDelivery/{selectedOptionId}")
    public ResponseDto<GetShippingAndDeliveryOrderSelectedOptionByUserDto.Response> getShippingAndDeliveryOrderSelectedOptionByUser(
            @AuthenticationPrincipal User user, @PathVariable UUID selectedOptionId) {

        ReadShippingOrderSelectedOptionDto dto = this.orderService.readShippingOrderSelectedOption(selectedOptionId,
                user.getId());

        GetShippingAndDeliveryOrderSelectedOptionByUserDto.Response response = this.getShippingAndDeliveryOrderSelectedOptionByUserDtoMapper
                .toResponse(dto);

        return new ResponseDto<>(ResponseCode.ORDER_SHIPPING_AND_DELIVERY_READ, response);
    }
}
