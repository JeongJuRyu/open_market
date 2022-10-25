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
import com.tmax.cm.superstore.error.exception.UnchangeableOrderStateException;

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
    private ShippingType shippingType;

    public void acceptStatus() throws UnchangeableOrderStateException {
        if (this.getShippingType() != ShippingType.SHIPPING_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setShippingType(ShippingType.SHIPPING_ACCEPT);
    }

    public void refuseStatus() throws UnchangeableOrderStateException {
        if (this.getShippingType() != ShippingType.SHIPPING_WAITING) {
            throw new UnchangeableOrderStateException();
        }
        setShippingType(ShippingType.SHIPPING_REFUSE);
    }

    public void processingStatus() throws UnchangeableOrderStateException {
        if (this.getShippingType() != ShippingType.SHIPPING_ACCEPT) {
            throw new UnchangeableOrderStateException();
        }
        setShippingType(ShippingType.SHIPPING_PROCESSING);
    }

    public void doneStatus() throws UnchangeableOrderStateException {
        if (!this.getShippingType().equals(ShippingType.SHIPPING_PROCESSING)) {
            throw new UnchangeableOrderStateException();
        }
        setShippingType(ShippingType.SHIPPING_DONE);
    }
}
