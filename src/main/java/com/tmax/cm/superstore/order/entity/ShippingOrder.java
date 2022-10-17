package com.tmax.cm.superstore.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ShippingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @OneToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(nullable = false)
    private Shop shop;

    @OneToMany(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "shippingOrderId")
    private List<ShippingOrderItem> shippingOrderItems;
}
