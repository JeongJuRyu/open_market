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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddress {
	@Id @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "UUID")
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
	@JoinColumn(name = "USER_ID")
	private User user;

	public void setDefaultAddress(DeliveryAddress deliveryAddress){
		this.isDefaultAddress = true;
		deliveryAddress.isDefaultAddress = false;
	}
}
