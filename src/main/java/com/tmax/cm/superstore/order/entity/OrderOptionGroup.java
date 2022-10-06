package com.tmax.cm.superstore.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tmax.cm.superstore.item.entity.OptionGroup;

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
public class OrderOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isNecessary = false;
    
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_order_option_group_option_group_id"), name = "optionGroupId", nullable = false)
    private OptionGroup optionGroup;
    
    @OneToMany(cascade = { CascadeType.PERSIST })
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_order_option_order_option_group_id"), name = "orderOptionGroupId", nullable = false)
    private List<OrderOption> orderOptions;
}
