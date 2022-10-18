package com.tmax.cm.superstore.pickup.service;

<<<<<<< HEAD

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
=======
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
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
                .recipient(recipient)
                .address(address)
                .mobile(mobile)
                .requests(request)
<<<<<<< HEAD
                .pickUpType(PickupType.PICKUP_WAITING)
=======
                .pickUpType(PickUpType.PICKUP_WAITING)
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
                .build();

        pickUpRepository.save(pickUp);
        return pickUp;
    }

    @Transactional
<<<<<<< HEAD
    public void acceptPick(Pickup pickUp) {
=======
    public void acceptPick(PickUp pickUp) {
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        pickUp.acceptState();
    }

    @Transactional
<<<<<<< HEAD
    public void refusePick(Pickup pickUp) {
=======
    public void refusePick(PickUp pickUp) {
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        pickUp.refuseState();
    }

    @Transactional
<<<<<<< HEAD
    public void readyPick(Pickup pickUp) {
=======
    public void readyPick(PickUp pickUp) {
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        pickUp.readyState();
    }

    @Transactional
<<<<<<< HEAD
    public void donePick(Pickup pickUp) {
=======
    public void donePick(PickUp pickUp) {
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        pickUp.doneState();
    }
}
