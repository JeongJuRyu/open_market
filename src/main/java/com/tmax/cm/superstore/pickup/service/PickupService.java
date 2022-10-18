package com.tmax.cm.superstore.pickup.service;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.pickup.repository.PickupRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PickupService {

    private final PickupRepository pickUpRepository;

    @Transactional
    public Pickup create(String recipient, String address, String mobile, String request) {
        Pickup pickUp = Pickup.builder()
                .recipient(recipient)
                .address(address)
                .mobile(mobile)
                .requests(request)
                .pickUpType(PickupType.PICKUP_WAITING)
                .build();

        pickUpRepository.save(pickUp);
        return pickUp;
    }

    @Transactional
    public void acceptPick(Pickup pickUp) {
        pickUp.acceptState();
    }

    @Transactional
    public void refusePick(Pickup pickUp) {
        pickUp.refuseState();
    }

    @Transactional
    public void readyPick(Pickup pickUp) {
        pickUp.readyState();
    }

    @Transactional
    public void donePick(Pickup pickUp) {
        pickUp.doneState();
    }
}
