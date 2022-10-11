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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ship_shipping_order_id"), name = "order_id", nullable = false)
    private ShippingOrder order;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingType finalstatus;


    public void acceptStatus() {
        if(this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_ACCEPT);
        }
    }

    public void refuseStatus() {
        if(this.getFinalstatus() == ShippingType.SHIPPING_WAITING) {
            setFinalstatus(ShippingType.SHIPPING_REFUSE);
        }
    }


}
