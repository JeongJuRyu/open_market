package com.tmax.cm.superstore.order.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.util.TransactionHandler;
import com.tmax.cm.superstore.order.controller.dto.GetShippingAndDeliveryOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.GetVisitAndPickupOrderSelectedOptionAllByShopDto;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetPickupOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.controller.dto.mapper.GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.order.service.OrderService;
import com.tmax.cm.superstore.pickup.service.PickupService;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.service.SellerService;
import com.tmax.cm.superstore.shipping.service.ShippingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/order/seller")
public class OrderSellerController {

    private final OrderService orderService;
    private final ShippingService shippingService;
    private final PickupService pickupService;
    private final SellerService sellerService;

    private final TransactionHandler transactionHandler;

    private final GetPickupOrderSelectedOptionAllByShopDtoMapper getPickupOrderSelectedOptionAllByShopDtoMapper;
    private final GetShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper getShippingAndDeliveryOrderSelectedOptionAllByShopDtoMapper;

    @GetMapping("/{sellerId}/visitAndPickup")
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

    @GetMapping("/{sellerId}/shippingAndDelivery")
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

    @PutMapping("/{sellerId}/shippingAndDelivery/{selectedOptionId}/acceptShipping")
    public ResponseDto<Void> putAcceptShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.acceptShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_ACCEPT_SHIPPING, null);
    }

    @PutMapping("/{sellerId}/shippingAndDelivery/{selectedOptionId}/rejectShipping")
    public ResponseDto<Void> putRejectShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.rejectShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_REJECT_SHIPPING, null);
    }

    @PutMapping("/{sellerId}/shippingAndDelivery/{selectedOptionId}/processingShipping")
    public ResponseDto<Void> putProcessingShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.processingShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_PROCESSING_SHIPPING, null);
    }

    @PutMapping("/{sellerId}/shippingAndDelivery/{selectedOptionId}/doneShipping")
    public ResponseDto<Void> putDoneShipping(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            ShippingOrderSelectedOption shippingOrderSelectedOption = this.orderService
                    .readShippingOrderSelectedOption(selectedOptionId, seller);

            this.shippingService.doneShipping(shippingOrderSelectedOption.getShipping().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_DONE_SHIPPING, null);
    }

    @PutMapping("/{sellerId}/visitAndPickup/{selectedOptionId}/acceptPick")
    public ResponseDto<Void> putAcceptPick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.acceptPick(pickupOrderSelectedOption.getPickup().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_ACCEPT_PICK, null);
    }

    @PutMapping("/{sellerId}/visitAndPickup/{selectedOptionId}/readyPick")
    public ResponseDto<Void> putReadyPick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.readyPick(pickupOrderSelectedOption.getPickup().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_READY_PICK, null);
    }

    @PutMapping("/{sellerId}/visitAndPickup/{selectedOptionId}/refusePick")
    public ResponseDto<Void> putRefusePick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.refusePick(pickupOrderSelectedOption.getPickup().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_REFUSE_PICK, null);
    }

    @PutMapping("/{sellerId}/visitAndPickup/{selectedOptionId}/donePick")
    public ResponseDto<Void> putDonePick(@PathVariable UUID selectedOptionId, @PathVariable UUID sellerId) {

        this.transactionHandler.runInSameTransaction(() -> {
            Seller seller = this.sellerService.findSeller(sellerId);

            PickupOrderSelectedOption pickupOrderSelectedOption = this.orderService
                    .readPickupOrderSelectedOption(selectedOptionId, seller);

            this.pickupService.donePick(pickupOrderSelectedOption.getPickup().getId());
        });

        return new ResponseDto<>(ResponseCode.ORDER_DONE_PICK, null);
    }
}
