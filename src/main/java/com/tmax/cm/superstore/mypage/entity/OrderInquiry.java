package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class OrderInquiry extends BaseTimeEntity {
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ORDER_INQUIRY_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean isReplied;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private PickupOrderSelectedOption pickupOrderSelectedOption;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private ShippingOrderSelectedOption shippingOrderSelectedOption;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private User user;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "orderInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
	private OrderInquiryReply orderInquiryReply;

	public void updateOrderInquiry(UpdateOrderInquiryRequestDto dto){
		this.content = dto.getContent();
	}

	public void postOrderInquiryReply(PostOrderInquiryReplyRequestDto dto) {
		this.isReplied = true;
		this.orderInquiryReply = OrderInquiryReply.builder()
			.id(UUID.randomUUID())
			.orderInquiry(this)
			.content(dto.getContent()).build();
	}

	public void deleteOrderInquiryReply(OrderInquiryReply orderInquiryReply){
		this.isReplied = false;
		this.orderInquiryReply = null;
	}
}




