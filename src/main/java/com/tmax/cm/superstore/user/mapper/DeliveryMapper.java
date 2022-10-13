package com.tmax.cm.superstore.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.user.dto.GetUserDeliveryInfoResponseDto;
import com.tmax.cm.superstore.user.entities.DeliveryAddress;

@Mapper(config = CommonMapperConfig.class)
public interface DeliveryMapper {
	List<GetUserDeliveryInfoResponseDto.DeliveryAddress> toDeliveriesDto(List<DeliveryAddress> deliveryAddresses);

	@Mapping(target = "id", source = "deliveryAddress.id")
	@Mapping(target = "recipient", source = "deliveryAddress.recipient")
	@Mapping(target = "isDefaultAddress", source = "deliveryAddress.isDefaultAddress")
	@Mapping(target = "mobile", source = "deliveryAddress.mobile")
	@Mapping(target = "address", source = "deliveryAddress.address")
	@Mapping(target = "requests", source = "deliveryAddress.requests")
	GetUserDeliveryInfoResponseDto.DeliveryAddress toDeliveryDto(DeliveryAddress deliveryAddress);
}
