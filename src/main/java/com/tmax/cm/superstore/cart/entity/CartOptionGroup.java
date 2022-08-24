package com.tmax.cm.superstore.cart.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
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
public class CartOptionGroup {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cart_option_group_selected_option_id"), name = "selectedOptionId", nullable = false)
    private SelectedOption selectedOption;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cart_option_group_option_group_id"), name = "optionGroupId", nullable = false)
    private OptionGroup optionGroup;

    @OneToMany(mappedBy = "cartOptionGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<CartOption> cartOptions;
}
