package com.tmax.cm.superstore.order.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	@Id @GeneratedValue(generator = "UUID" )
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ORDER_ITEM_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private int count;

	//추후 옵션 테이블 추가 하여 세분화 예상
	@Column(nullable = false)
	private String options;

	@Column(nullable = false)
	private String imageUrl;

	@OneToMany(mappedBy = "orderItem")
	@Builder.Default
	private List<OrderInquiry> orderInquiries = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Item item;


}
