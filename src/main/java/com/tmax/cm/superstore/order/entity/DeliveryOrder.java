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
import javax.persistence.OrderBy;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.seller.entity.Seller;

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
public class DeliveryOrder extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(nullable = false)
    private Seller seller;

    @OneToMany(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "deliveryOrderId")
    @OrderBy("id DESC")
    private List<ShippingOrderItem> shippingOrderItems;
}
