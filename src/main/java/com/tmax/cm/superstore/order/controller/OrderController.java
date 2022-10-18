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
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartItemService;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.util.TransactionHandler;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.PostOrderDto;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.order.service.OrderService;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.payment.service.PaymentService;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.pickup.service.PickupService;
import com.tmax.cm.superstore.purchaseOrder.service.PurchaseOrderService;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.service.SellerService;
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
    private final PickupService pickupService;
    private final SellerService sellerService;

    private final TransactionHandler transactionHandler;

    private final GetPickupOrderSelectedOptionAllByShopDtoMapper getPickupOrderSelectedOptionAllByShopDtoMapper;
    private final GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper getShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper;

    @PostMapping
    public ResponseDto<Void> postCreateOrder(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PostOrderDto.Request request) {

        this.transactionHandler.runInSameTransaction(() -> {
            List<CartItem> cartItems = this.cartItemService.read(request.getCartItemIds());
            Payment payment = this.paymentService.create(request);
            PurchaseOrderDto purchaseOrderDto = this.purchaseOrderService.read(cartItems);
            Shipping shippingOrderShipping = null;
            Shipping deliveryOrderShipping = null;
            Pickup visitOrderPickup = null;
            Pickup pickupOrderPickup = null;

            // TODO refactor: 주문할 상품이 없어도 shipping, pickup이 생성될 수 있음
            if (request.getShippingRecipientInfo() != null) {
                shippingOrderShipping = this.shippingService.create(request.getShippingRecipientInfo().getRecipient(),
                        request.getShippingRecipientInfo().getAddress(), request.getShippingRecipientInfo().getMobile(),
                        request.getShippingRecipientInfo().getRequests());
            }

            if (request.getDeliveryRecipientInfo() != null) {
                deliveryOrderShipping = this.shippingService.create(request.getDeliveryRecipientInfo().getRecipient(),
                        request.getDeliveryRecipientInfo().getAddress(), request.getDeliveryRecipientInfo().getMobile(),
                        request.getDeliveryRecipientInfo().getRequests());
            }

            if (request.getVisitRecipientInfo() != null) {
                visitOrderPickup = this.pickupService.create(request.getVisitRecipientInfo().getRecipient(), null,
                        request.getVisitRecipientInfo().getMobile(), request.getVisitRecipientInfo().getRequests());
            }

            if (request.getPickupRecipientInfo() != null) {
                pickupOrderPickup = this.pickupService.create(request.getPickupRecipientInfo().getRecipient(), null,
                        request.getPickupRecipientInfo().getMobile(), request.getPickupRecipientInfo().getRequests());
            }

            this.orderService.create(user, payment, purchaseOrderDto, shippingOrderShipping, visitOrderPickup,
                    deliveryOrderShipping, pickupOrderPickup);

            this.cartItemService.delete(cartItems);
        });

        return new ResponseDto<>(ResponseCode.ORDER_CREATE, null);
    }

    @GetMapping("/visitAndPickup/seller/{sellerId}")
    public ResponseDto<GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response> getVisitAndPickupOrderSelectedOptionAll(
            @PathVariable UUID sellerId) {

        Seller seller = this.sellerService.findSeller(sellerId);

        List<VisitOrder> visitOrders = this.orderService.readVisitOrders(seller);
        List<PickupOrder> pickupOrders = this.orderService.readPickupOrders(seller);

        GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response response = this.getPickupOrderSelectedOptionAllByShopDtoMapper
                .toResponse(visitOrders, pickupOrders);

        return new ResponseDto<>(ResponseCode.ORDER_VISIT_AND_PICKUP_READ, response);
    }

    @GetMapping("/shippingAndDelivery/seller/{sellerId}")
    public ResponseDto<GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response> getShippingAndDeliveryOrderSelectedOptionAll(
            @PathVariable UUID sellerId) {

        Seller seller = this.sellerService.findSeller(sellerId);

        List<ShippingOrder> shippingOrders = this.orderService.readShippingOrders(seller);
        List<DeliveryOrder> deliveryOrders = this.orderService.readDeliveryOrders(seller);

        GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response response = this.getShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper
                .toResponse(shippingOrders, deliveryOrders);

        return new ResponseDto<>(ResponseCode.ORDER_SHIPPING_AND_DELIVERY_READ, response);
    }
}
