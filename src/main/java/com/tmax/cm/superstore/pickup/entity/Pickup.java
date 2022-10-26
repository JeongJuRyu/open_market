package com.tmax.cm.superstore.pickup.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.error.exception.UnchangeableOrderStateException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Pickup extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String mobile;

    private String requests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PickupType pickupType;

    public void acceptState() throws UnchangeableOrderStateException {
        if (getPickupType() != PickupType.PICKUP_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setPickupType(PickupType.PICKUP_ACCEPT);
    }

    public void refuseState() throws UnchangeableOrderStateException {
        if (getPickupType() != PickupType.PICKUP_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setPickupType(PickupType.PICKUP_REFUSE);
    }

    public void doneState() throws UnchangeableOrderStateException {
        if (getPickupType() != PickupType.PICKUP_READY) {
            throw new UnchangeableOrderStateException();
        }
        setPickupType(PickupType.PICKUP_DONE);
    }

    public void readyState() throws UnchangeableOrderStateException {
        if (getPickupType() != PickupType.PICKUP_ACCEPT) {
            throw new UnchangeableOrderStateException();
        }
        setPickupType(PickupType.PICKUP_READY);
    }

}
