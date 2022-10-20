package com.tmax.cm.superstore.order.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.service.CartItemService;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.util.TransactionHandler;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByUserDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByUserDto;
import com.tmax.cm.superstore.order.controller.dto.PostOrderDto;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionAllByUserDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
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
    private final GetPickupOrderSelectedOptionAllByUserDtoMapper getPickupOrderSelectedOptionAllByUserDtoMapper;
    private final GetShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper getShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper;

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
    public ResponseDto<GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response> getVisitAndPickupOrderSelectedOptionAllByShop(
            @PathVariable UUID sellerId, @RequestParam(required = false) PickupType pickupType) {
        List<VisitOrder> visitOrders;
        List<PickupOrder> pickupOrders;

        Seller seller = this.sellerService.findSeller(sellerId);

        if (pickupType == null) {
            visitOrders = this.orderService.readVisitOrders(seller);
            pickupOrders = this.orderService.readPickupOrders(seller);
        } else {
            visitOrders = this.orderService.readVisitOrdersByPickupType(seller, pickupType);
            pickupOrders = this.orderService.readPickupOrdersByPickupType(seller, pickupType);
        }

        GetVisitAndPickupOrderSelectedOptionAllByShopDto.Response response = this.getPickupOrderSelectedOptionAllByShopDtoMapper
                .toResponse(visitOrders, pickupOrders);

        return new ResponseDto<>(ResponseCode.ORDER_VISIT_AND_PICKUP_READ, response);
    }

    @GetMapping("/shippingAndDelivery/seller/{sellerId}")
    public ResponseDto<GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response> getShippingAndDeliveryOrderSelectedOptionAll(
            @PathVariable UUID sellerId, @RequestParam(required = false) ShippingType shippingType) {

        List<ShippingOrder> shippingOrders;
        List<DeliveryOrder> deliveryOrders;

        Seller seller = this.sellerService.findSeller(sellerId);

        if (shippingType == null) {
            shippingOrders = this.orderService.readShippingOrders(seller);
            deliveryOrders = this.orderService.readDeliveryOrders(seller);
        } else {
            shippingOrders = this.orderService.readShippingOrdersByShippingType(seller, shippingType);
            deliveryOrders = this.orderService.readDeliveryOrdersByShippingType(seller, shippingType);
        }

        GetShippingAndDeliveryOrderSelectedOptionAllByShopDto.Response response = this.getShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper
                .toResponse(shippingOrders, deliveryOrders);

        return new ResponseDto<>(ResponseCode.ORDER_SHIPPING_AND_DELIVERY_READ, response);
    }

    @GetMapping("/visitAndPickup/user")
    public ResponseDto<GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response> getVisitAndPickupOrderSelectedOptionAllByUser(
            @AuthenticationPrincipal User user, @RequestParam(required = false) PickupType pickupType) {
        List<VisitOrder> visitOrders;
        List<PickupOrder> pickupOrders;

        if (pickupType == null) {
            visitOrders = this.orderService.readVisitOrders(user);
            pickupOrders = this.orderService.readPickupOrders(user);
        } else {
            visitOrders = this.orderService.readVisitOrdersByPickupType(user, pickupType);
            pickupOrders = this.orderService.readPickupOrdersByPickupType(user, pickupType);
        }

        GetVisitAndPickupOrderSelectedOptionAllByUserDto.Response response = this.getPickupOrderSelectedOptionAllByUserDtoMapper
                .toResponse(visitOrders, pickupOrders);

        return new ResponseDto<>(ResponseCode.ORDER_VISIT_AND_PICKUP_READ, response);
    }

    @GetMapping("/shippingAndDelivery/user")
    public ResponseDto<GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response> getShippingAndDeliveryOrderSelectedOptionAllByUser(
            @AuthenticationPrincipal User user, @RequestParam(required = false) ShippingType shippingType) {

        List<ShippingOrder> shippingOrders;
        List<DeliveryOrder> deliveryOrders;

        if (shippingType == null) {
            shippingOrders = this.orderService.readShippingOrders(user);
            deliveryOrders = this.orderService.readDeliveryOrders(user);
        } else {
            shippingOrders = this.orderService.readShippingOrdersByShippingType(user, shippingType);
            deliveryOrders = this.orderService.readDeliveryOrdersByShippingType(user, shippingType);
        }

        GetShippingAndDeliveryOrderSelectedOptionAllByUserDto.Response response = this.getShippingAndDeliveryOrderSelectedOptionAllByUserDtoMapper
                .toResponse(shippingOrders, deliveryOrders);

        return new ResponseDto<>(ResponseCode.ORDER_SHIPPING_AND_DELIVERY_READ, response);
    }

    @PutMapping("/shippingAndDelivery/{selectedOptionId}/seller/{sellerId}/acceptShipping")
    public ResponseDto<Void> putAcceptShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.acceptShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_ACCEPT_SHIPPING, null);
    }

    @PutMapping("/shippingAndDelivery/{selectedOptionId}/seller/{sellerId}/rejectShipping")
    public ResponseDto<Void> putRejectShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.rejectShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_REJECT_SHIPPING, null);
    }

    @PutMapping("/shippingAndDelivery/{selectedOptionId}/seller/{sellerId}/doneShipping")
    public ResponseDto<Void> putDoneShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.doneShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_DONE_SHIPPING, null);
    }

    @PutMapping("/visitAndPickup/{selectedOptionId}/seller/{sellerId}/acceptPick")
    public ResponseDto<Void> putAcceptPick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.acceptPick(pickupOrderSelectedOption.getPickup());
        });

        return new ResponseDto<>(ResponseCode.ORDER_ACCEPT_PICK, null);
    }

    @PutMapping("/visitAndPickup/{selectedOptionId}/seller/{sellerId}/readyPick")
    public ResponseDto<Void> putReadyPick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.readyPick(pickupOrderSelectedOption.getPickup());
        });

        return new ResponseDto<>(ResponseCode.ORDER_READY_PICK, null);
    }

    @PutMapping("/visitAndPickup/{selectedOptionId}/seller/{sellerId}/refusePick")
    public ResponseDto<Void> putRefusePick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.refusePick(pickupOrderSelectedOption.getPickup());
        });

        return new ResponseDto<>(ResponseCode.ORDER_REFUSE_PICK, null);
    }

    @PutMapping("/visitAndPickup/{selectedOptionId}/seller/{sellerId}/donePick")
    public ResponseDto<Void> putDonePick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.donePick(pickupOrderSelectedOption.getPickup());
        });

        return new ResponseDto<>(ResponseCode.ORDER_DONE_PICK, null);
    }
}
