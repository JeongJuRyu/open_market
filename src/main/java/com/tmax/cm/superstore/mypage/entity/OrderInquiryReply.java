package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;

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
public class OrderInquiryReply extends BaseTimeEntity {

	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String content;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private OrderInquiry orderInquiry;

	public void updateOrderInquiryReply(String content){
		this.content = content;
	}

	public void deleteOrderInquiry(){
		this.orderInquiry = null;
	}
}
