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
