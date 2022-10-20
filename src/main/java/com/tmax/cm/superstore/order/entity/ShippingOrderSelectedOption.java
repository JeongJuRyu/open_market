package com.tmax.cm.superstore.order.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.shipping.entity.Shipping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class ShippingOrderSelectedOption extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    @Builder.Default
    private Integer count = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer amount = 0;

    @OneToMany(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "shippingOrderSelectedOptionId")
    private List<OrderOptionGroup> orderOptionGroups;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Shipping shipping;
}
