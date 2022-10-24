package com.tmax.cm.superstore.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.tmax.cm.superstore.item.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class PickupOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer amount;

	@OneToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "pickupOrderItemId")
	@OrderBy("createdAt DESC")
	private List<PickupOrderSelectedOption> pickupOrderSelectedOptions;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Item item;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(nullable = true)
	private ShippingFee shippingFee;
}
