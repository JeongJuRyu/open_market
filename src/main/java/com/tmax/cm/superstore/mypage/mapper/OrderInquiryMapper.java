package com.tmax.cm.superstore.mypage.mapper;

import java.util.List;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface OrderInquiryMapper {
	List<GetAllOrderInquiryResponseDto.OrderInquiry> toOrderInquiries (List<OrderInquiry> orderInquiries);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "isAnswered", source = "isAnswered")
	GetAllOrderInquiryResponseDto.OrderInquiry toOrderInquiry(OrderInquiry orderInquiry);

	GetOrderInquiryResponseDto toSingleOrderInquiry(OrderInquiry orderInquiry);
}
