package com.tmax.cm.superstore.seller.entity;

import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.ModifySellerInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "seller")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "SellerBuilder")
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

	@Column(nullable = false)
	private boolean isDeleted;

	public static SellerBuilder builder(CreateSellerDto.Request createSellerRequestDto) {
		return SellerBuilder()
			.loginId(createSellerRequestDto.getSellerInfo().getLoginId())
			.password(createSellerRequestDto.getSellerInfo().getPassword())
			.sellerName(createSellerRequestDto.getSellerInfo().getSellerName())
			.sellerEmail(createSellerRequestDto.getSellerInfo().getSellerEmail())
			.sellerPhoneNum(createSellerRequestDto.getSellerInfo().getSellerPhoneNum());
	}

	public void modifySellerInfo(ModifySellerInfoDto.Request modifySellerInfoRequestDto) {
		this.password = modifySellerInfoRequestDto.getPassword();
		this.sellerEmail = modifySellerInfoRequestDto.getSellerEmail();
		this.sellerPhoneNum = modifySellerInfoRequestDto.getSellerPhoneNum();
	}
}
