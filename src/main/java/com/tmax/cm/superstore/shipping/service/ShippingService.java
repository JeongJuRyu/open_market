package com.tmax.cm.superstore.shipping.service;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
                .finalstatus(ShippingType.SHIPPING_WAITING)
                .build();

        repository.save(shipping);
        return shipping;
    }

    @Transactional
    public void acceptShipping(Shipping shipping) {
        shipping.acceptStatus();
    }

    @Transactional
    public void rejectShipping(Shipping shipping) {
        shipping.refuseStatus();
    }

    @Transactional
    public void doneShipping(Shipping shipping) {
        shipping.doneStatus();
    }
}
