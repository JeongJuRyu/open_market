package com.tmax.cm.superstore.user.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress extends BaseTimeEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String recipient;

	@Column(nullable = false)
	private Boolean isDefaultAddress;

	@Column(nullable = false)
	private String mobile;

	@Column(nullable = false)
	private String address;

	private String requests;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public void setDefaultAddress(Boolean isDefaultAddress){
		this.isDefaultAddress = isDefaultAddress;
	}

	public void updateDeliveryAddress(UpdateDeliveryInfoRequestDto dto){
		this.isDefaultAddress = dto.getIsDefaultAddress();
		this.address = dto.getAddress();
		this.mobile = dto.getMobile();
		this.recipient = dto.getRecipient();
		this.requests = dto.getRequests();
	}
}
