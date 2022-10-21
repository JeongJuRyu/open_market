package com.tmax.cm.superstore.pickup.service;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.pickup.repository.PickupRepository;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

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
                .pickupType(PickupType.PICKUP_WAITING)
                .build();

        pickUpRepository.save(pickUp);
        return pickUp;
    }

    @Transactional
    public void acceptPick(UUID pickupId) {
        Pickup pickup = pickUpRepository.findWithIdForUpdate(pickupId);
        pickup.acceptState();
    }

    @Transactional
    public void refusePick(UUID pickupId) {
        Pickup pickup = pickUpRepository.findWithIdForUpdate(pickupId);
        pickup.refuseState();
    }

    @Transactional
    public void readyPick(UUID pickupId) {
        Pickup pickup = pickUpRepository.findWithIdForUpdate(pickupId);
        pickup.readyState();
    }

    @Transactional
    public void donePick(UUID pickupId) {
        Pickup pickup = pickUpRepository.findWithIdForUpdate(pickupId);
        pickup.doneState();
    }
}
