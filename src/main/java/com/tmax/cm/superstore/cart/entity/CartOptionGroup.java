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

    @OneToMany(mappedBy = "cartOptionGroup", cascade = {CascadeType.PERSIST})
	private List<CartOption> cartOptions;
}