package com.tmax.cm.superstore.shipping.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.repository.ShippingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShippingService {

    private final ShippingRepository repository;

    @Transactional
    public Shipping create(String recipient, String address, String mobile, String request) {
        Shipping shipping = Shipping.builder()
                .address(address)
                .recipient(recipient)
                .mobile(mobile)
                .requests(request)
                .shippingType(ShippingType.SHIPPING_WAITING)
                .build();

        repository.save(shipping);
        return shipping;
    }

    @Transactional
    public void acceptShipping(UUID shippingId) throws PessimisticLockingFailureException {
        Shipping shipping = this.repository.findWithIdForUpdate(shippingId);
        shipping.acceptStatus();
    }

    @Transactional
    public void rejectShipping(UUID shippingId) throws PessimisticLockingFailureException {
        Shipping shipping = this.repository.findWithIdForUpdate(shippingId);
        shipping.refuseStatus();
    }

    @Transactional
    public void processingShipping(UUID shippingId) throws PessimisticLockingFailureException {
        Shipping shipping = this.repository.findWithIdForUpdate(shippingId);
        shipping.processingStatus();
    }

    @Transactional
    public void doneShipping(UUID shippingId) throws PessimisticLockingFailureException {
        Shipping shipping = this.repository.findWithIdForUpdate(shippingId);
        shipping.doneStatus();
    }
}
