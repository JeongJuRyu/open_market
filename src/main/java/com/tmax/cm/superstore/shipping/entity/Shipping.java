package com.tmax.cm.superstore.ship.entity;

import com.tmax.cm.superstore.order.entity.Order;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Ship {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ship_order_id"), name = "order_id", nullable = false)
    private Order order;


}
