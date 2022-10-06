package com.tmax.cm.superstore.order.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tmax.cm.superstore.item.entity.Item;

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
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private Integer amount;

	@OneToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_order_selected_option_order_item_id"), name = "orderItemId", nullable = false)
	private List<OrderSelectedOption> orderSelectedOptions;

	@Column(nullable = false)
	private String imageUrl;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Item item;

	@OneToOne(mappedBy = "orderItem")
	private CustomerInquiry customerInquiry;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_order_item_shipping_fee_id"), name = "shippingFeeId", nullable = true)
	private ShippingFee shippingFee;

}
