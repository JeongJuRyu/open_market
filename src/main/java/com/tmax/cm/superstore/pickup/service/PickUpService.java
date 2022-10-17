package com.tmax.cm.superstore.pickup.service;

import com.tmax.cm.superstore.code.PickUpType;
import com.tmax.cm.superstore.pickup.entity.PickUp;
import com.tmax.cm.superstore.pickup.repository.PickUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.transaction.Transactional;

@Setter
@RequiredArgsConstructor
public class PickUpService {

    private final PickUpRepository pickUpRepository;

    @Transactional
    public PickUp create(String recipient, String address, String mobile, String request) {
        PickUp pickUp = PickUp.builder()
                .recipient(recipient)
                .address(address)
                .mobile(mobile)
                .requests(request)
                .pickUpType(PickUpType.PICKUP_WAITING)
                .build();

        pickUpRepository.save(pickUp);
        return pickUp;
    }

    @Transactional
    public void acceptPick(PickUp pickUp) {
        pickUp.acceptState();
    }

    @Transactional
    public void refusePick(PickUp pickUp) {
        pickUp.refuseState();
    }

    @Transactional
    public void readyPick(PickUp pickUp) {
        pickUp.readyState();
    }

    @Transactional
    public void donePick(PickUp pickUp) {
        pickUp.doneState();
    }
}
