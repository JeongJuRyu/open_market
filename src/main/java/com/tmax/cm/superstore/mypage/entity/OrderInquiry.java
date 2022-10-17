package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.user.entities.User;

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
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Boolean isAnswered;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private PickupOrderItem orderItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private User user;

	@OneToOne(mappedBy = "orderInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
	private OrderInquiryReply orderInquiryReply;
	public void updateOrderInquiry(UpdateOrderInquiryRequestDto dto){
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}

	public void postOrderInquiryReply(OrderInquiryReply orderInquiryReply) {
		this.orderInquiryReply = orderInquiryReply;
	}

	public void deleteOrderInquiryReply(){
		this.orderInquiryReply = null;
	}
}




