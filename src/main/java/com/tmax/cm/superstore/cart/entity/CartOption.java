package com.tmax.cm.superstore.cart.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class CartOption {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cart_option_cart_option_group_id"), name = "cartOptionGroupId", nullable = false)
    private CartOptionGroup cartOptionGroup;

    @Column(nullable = false)
    @Builder.Default
    private Integer count = 0;
}
