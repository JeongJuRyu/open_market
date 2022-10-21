package com.tmax.cm.superstore.develop.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.develop.service.DevelopOrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/develop/order")
public class DevelopOrderController {

    private final DevelopOrderService developOrderService;

    @PutMapping("/shippingAndDelivery/{selectedOptionId}")
    public ResponseDto<Void> putSetShippingType(@PathVariable UUID selectedOptionId,
            @RequestParam ShippingType shippingType) {

        this.developOrderService.setShippingOrderSelectedOption(selectedOptionId, shippingType);

        return new ResponseDto<>(ResponseCode.DEVELOP_SHIPPING_TYPE_UPDATE, null);
    }

    @PutMapping("/visitAndPickup/{selectedOptionId}")
    public ResponseDto<Void> putSetPickupType(@PathVariable UUID selectedOptionId,
            @RequestParam PickupType pickupType) {

        this.developOrderService.setPickupOrderSelectedOption(selectedOptionId, pickupType);

        return new ResponseDto<>(ResponseCode.DEVELOP_PICKUP_TYPE_UPDATE, null);
    }
}
