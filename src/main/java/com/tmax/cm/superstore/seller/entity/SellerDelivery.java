package com.tmax.cm.superstore.seller.entity;

import com.tmax.cm.superstore.seller.dto.CreateSellerDeliveryDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sellerDelivery")
@Access(AccessType.FIELD)
@Builder(builderMethodName = "SellerDeliveryBuilder")
public class SellerDelivery {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID sellerDeliveryId;

	@Column
	private String shipmentAddress;

	@Column
	private String shipmentAddressDetail;

	@Column
	private String returnAddress;

	@Column
	private String returnAddressDetail;

	@Column(nullable = false)
	private boolean isDeleted;

	@Column(nullable = false)
	private boolean isRepresent;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId")
	private Seller sellerId;

	public static SellerDeliveryBuilder builder(Seller seller, CreateSellerDto.Request createSellerRequestDto) {
		return SellerDeliveryBuilder()
			.shipmentAddress(createSellerRequestDto.getSellerDeliveryInfo().getShipmentAddress())
			.shipmentAddressDetail(createSellerRequestDto.getSellerDeliveryInfo().getShipmentAddressDetail())
			.returnAddress(createSellerRequestDto.getSellerDeliveryInfo().getReturnAddress())
			.returnAddressDetail(createSellerRequestDto.getSellerDeliveryInfo().getReturnAddressDetail())
			.sellerId(seller);
	}

	public static SellerDeliveryBuilder builder(Seller seller, CreateSellerDeliveryDto.Request createSellerRequestDto) {
		return SellerDeliveryBuilder()
			.shipmentAddress(createSellerRequestDto.getShipmentAddress())
			.shipmentAddressDetail(createSellerRequestDto.getShipmentAddressDetail())
			.returnAddress(createSellerRequestDto.getReturnAddress())
			.returnAddressDetail(createSellerRequestDto.getReturnAddressDetail())
			.sellerId(seller);
	}

	public void modifyRepresent() {
		if (this.isRepresent) {
			this.isRepresent = false;
		} else {
			this.isRepresent = true;
		}
	}
}
