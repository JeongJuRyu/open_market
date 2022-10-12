package com.tmax.cm.superstore.shipping.entity;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Shipping {

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

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public void acceptStatus() {
        if(this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_ACCEPT);
            setTimestamp(LocalDateTime.now());
        }
    }

    public void refuseStatus() {
        if(this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_REFUSE);
            setTimestamp(LocalDateTime.now());
        }
    }

    public void doneStatus() {
        if(this.getFinalstatus().equals(ShippingType.SHIPPING_ACCEPT)) {
            setFinalstatus(ShippingType.SHIPPING_DONE);
            setTimestamp(LocalDateTime.now());
        }
    }


}
