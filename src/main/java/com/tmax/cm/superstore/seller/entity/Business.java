package com.tmax.cm.superstore.seller.entity;

import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.ModifyBizInfoDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "business")
@Access(AccessType.FIELD)
@Builder(builderMethodName = "BusinessBuilder")
public class Business {

	@Id
	@Column(name = "sellerId", columnDefinition = "BINARY(16)")
	private UUID bizId;

	@Column
	private String bizNum;

	@Column
	private String bizName;

	@Column
	private String bizOwner;

	@Column
	private String bizAddress;

	// 통신판매업 신고번호
	@Column
	private String reportNumber;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Seller sellerId;

	public static BusinessBuilder builder(Seller seller, CreateSellerDto.Request createSellerRequestDto) {
		return BusinessBuilder()
			.bizId(seller.getSellerId())
			.bizNum(createSellerRequestDto.getBizInfo().getBizNum())
			.bizName(createSellerRequestDto.getBizInfo().getBizName())
			.bizOwner(createSellerRequestDto.getBizInfo().getBizOwner())
			.bizAddress(createSellerRequestDto.getBizInfo().getBizAddress())
			.reportNumber(createSellerRequestDto.getBizInfo().getReportNumber())
			.sellerId(seller);
	}

	public void modifyBizInfo(ModifyBizInfoDto.Request modifyBizInfoRequestDto) {
		this.bizNum = modifyBizInfoRequestDto.getBizNum();
		this.bizName = modifyBizInfoRequestDto.getBizName();
		this.bizOwner = modifyBizInfoRequestDto.getBizOwner();
		this.bizAddress = modifyBizInfoRequestDto.getBizAddress();
		this.reportNumber = modifyBizInfoRequestDto.getReportNumber();
	}
}
