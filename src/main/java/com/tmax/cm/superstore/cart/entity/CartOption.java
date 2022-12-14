package com.tmax.cm.superstore.cart.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.item.entity.Option;

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
public class CartOption extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(nullable = false)
    private CartOptionGroup cartOptionGroup;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Option option;

    @Column(nullable = false)
    @Builder.Default
    private Integer count = 0;
}
