package com.tmax.cm.superstore.pickup.entity;

<<<<<<< HEAD
import com.tmax.cm.superstore.code.PickupType;
=======
import com.tmax.cm.superstore.code.PickUpType;
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
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
<<<<<<< HEAD

public class Pickup extends BaseTimeEntity {
=======
public class PickUp extends BaseTimeEntity {
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)

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
<<<<<<< HEAD
    private PickupType pickUpType;

    public void acceptState() {
        if(getPickUpType() == PickupType.PICKUP_WAITING) {
            setPickUpType(PickupType.PICKUP_ACCEPT);
=======
    private PickUpType pickUpType;

    public void acceptState() {
        if(getPickUpType() == PickUpType.PICKUP_WAITING) {
            setPickUpType(PickUpType.PICKUP_ACCEPT);
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        }
    }

    public void refuseState() {
<<<<<<< HEAD
        if(getPickUpType() == PickupType.PICKUP_WAITING) {
            setPickUpType(PickupType.PICKUP_REFUSE);
=======
        if(getPickUpType() == PickUpType.PICKUP_WAITING) {
            setPickUpType(PickUpType.PICKUP_REFUSE);
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        }
    }

    public void doneState() {
<<<<<<< HEAD
        if(getPickUpType() == PickupType.PICKUP_READY) {
            setPickUpType(PickupType.PICKUP_DONE);
=======
        if(getPickUpType() == PickUpType.PICKUP_READY) {
            setPickUpType(PickUpType.PICKUP_DONE);
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        }
    }

    public void readyState() {
<<<<<<< HEAD
        if (getPickUpType() == PickupType.PICKUP_ACCEPT) {
            setPickUpType(PickupType.PICKUP_READY);
=======
        if(getPickUpType() == PickUpType.PICKUP_ACCEPT) {
            setPickUpType(PickUpType.PICKUP_READY);
>>>>>>> 855b3b0 (refactor: 픽업/방문수령 이름 수정)
        }
    }
}
