package com.tmax.cm.superstore.pickup.entity;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
    private PickupType pickUpType;

    public void acceptState() {
        if(getPickUpType() == PickupType.PICKUP_WAITING) {
            setPickUpType(PickupType.PICKUP_ACCEPT);
        }
    }

    public void refuseState() {
        if(getPickUpType() == PickupType.PICKUP_WAITING) {
            setPickUpType(PickupType.PICKUP_REFUSE);
        }
    }

    public void doneState() {
        if(getPickUpType() == PickupType.PICKUP_READY) {
            setPickUpType(PickupType.PICKUP_DONE);
        }
    }

    public void readyState() {
        if (getPickUpType() == PickupType.PICKUP_ACCEPT) {
            setPickUpType(PickupType.PICKUP_READY);
        }
    }
}
