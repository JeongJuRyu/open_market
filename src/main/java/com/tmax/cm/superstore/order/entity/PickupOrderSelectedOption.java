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
import com.tmax.cm.superstore.pickup.entity.Pickup;
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
@Entity
public class PickupOrderSelectedOption extends BaseTimeEntity {

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
    @JoinColumn(name = "pickupOrderSelectedOptionId")
    private List<OrderOptionGroup> orderOptionGroups;

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pickup pickup;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(nullable = false)
    private Seller seller;
}
