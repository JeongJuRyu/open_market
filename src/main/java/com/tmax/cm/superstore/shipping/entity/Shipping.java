package com.tmax.cm.superstore.shipping.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.common.entity.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Shipping extends BaseTimeEntity {

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
    private ShippingType finalstatus;

    public void acceptStatus() {
        if (this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_ACCEPT);
        }
    }

    public void refuseStatus() {
        if (this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_REFUSE);
        }
    }

    public void doneStatus() {
        if (this.getFinalstatus().equals(ShippingType.SHIPPING_ACCEPT)) {
            setFinalstatus(ShippingType.SHIPPING_DONE);
        }
    }

}
