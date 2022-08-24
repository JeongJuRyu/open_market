package com.tmax.cm.superstore.seller.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "seller")
@Access(AccessType.FIELD)
public class Seller {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID sellerId;

	@Column
	private String loginId;

	@Column
	private String password;

	@Column
	private String sellerName;

	@Column
	private String sellerEmail;

	@Column
	private String sellerPhoneNum;
}
