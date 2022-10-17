package com.tmax.cm.superstore.pickup.entity;

import com.tmax.cm.superstore.code.PickUpType;
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
public class PickUp extends BaseTimeEntity {

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
    private PickUpType pickUpType;

    public void acceptState() {
        if(getPickUpType() == PickUpType.PICKUP_WAITING) {
            setPickUpType(PickUpType.PICKUP_ACCEPT);
        }
    }

    public void refuseState() {
        if(getPickUpType() == PickUpType.PICKUP_WAITING) {
            setPickUpType(PickUpType.PICKUP_REFUSE);
        }
    }

    public void doneState() {
        if(getPickUpType() == PickUpType.PICKUP_READY) {
            setPickUpType(PickUpType.PICKUP_DONE);
        }
    }

    public void readyState() {
        if(getPickUpType() == PickUpType.PICKUP_ACCEPT) {
            setPickUpType(PickUpType.PICKUP_READY);
        }
    }
}
