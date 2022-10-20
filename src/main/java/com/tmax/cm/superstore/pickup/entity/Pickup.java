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
import lombok.*;

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

    @Column(nullable = false)
    private String address;

    private String requests;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PickupType pickupType;

    public void acceptState() {
        if(getPickupType() == PickupType.PICKUP_WAITING) {
            setPickupType(PickupType.PICKUP_ACCEPT);
        }
    }

    public void refuseState() {
        if(getPickupType() == PickupType.PICKUP_WAITING) {
            setPickupType(PickupType.PICKUP_REFUSE);
        }
    }

    public void doneState() {
        if(getPickupType() == PickupType.PICKUP_READY) {
            setPickupType(PickupType.PICKUP_DONE);
        }
    }

    public void readyState() {
        if(getPickupType() == PickupType.PICKUP_ACCEPT) {
            setPickupType(PickupType.PICKUP_READY);
        }
    }

}
