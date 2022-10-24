package com.tmax.cm.superstore.develop.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.error.exception.EntityNotFoundException;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.repository.PickupOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderSelectedOptionRepository;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.pickup.repository.PickupRepository;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.repository.ShippingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DevelopOrderService {

    private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
    private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
    private final ShippingRepository shippingRepository;
    private final PickupRepository pickupRepository;

    @Transactional
    public void setShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId, ShippingType shippingType) {
        ShippingOrderSelectedOption shippingOrderSelectedOption = this.shippingOrderSelectedOptionRepository
                .findById(shippingOrderSelectedOptionId)
                .orElseThrow(() -> EntityNotFoundException.of(ShippingOrderSelectedOption.class));

        Shipping shipping = this.shippingRepository.findWithIdForUpdate(shippingOrderSelectedOption.getShipping().getId());

        shipping.setShippingType(shippingType);
    }

    @Transactional
    public void setPickupOrderSelectedOption(UUID PickupOrderSelectedOptionId, PickupType pickupType) {
        PickupOrderSelectedOption pickupOrderSelectedOption = this.pickupOrderSelectedOptionRepository
                .findById(PickupOrderSelectedOptionId)
                .orElseThrow(() -> EntityNotFoundException.of(PickupOrderSelectedOption.class));

        Pickup pickup = this.pickupRepository.findWithIdForUpdate(pickupOrderSelectedOption.getPickup().getId());

        pickup.setPickupType(pickupType);
    }
}
