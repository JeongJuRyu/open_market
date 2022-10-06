package com.tmax.cm.superstore.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tmax.cm.superstore.shop.entity.Shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class PickupOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_pickup_order_order_id"), name = "orderId", nullable = false)
    private Order order;

    @OneToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_pickup_order_shop_id"), name = "shopId", nullable = false)
    private Shop shop;

    @OneToMany(cascade = { CascadeType.PERSIST })
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_order_item_pickup_order_id"), name = "pickupOrderId", nullable = true)
    private List<OrderItem> orderItems;
}
