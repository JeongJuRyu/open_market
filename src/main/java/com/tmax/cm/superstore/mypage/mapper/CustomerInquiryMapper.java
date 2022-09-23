package com.tmax.cm.superstore.mypage.mapper;

import java.util.List;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllCustomerInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetCustomerInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface CustomerInquiryMapper {
	List<GetAllCustomerInquiryForSellerResponseDto.CustomerInquiry> toCustomerInquiries(List<CustomerInquiry> customerInquiries);

	@Mapping(target = "customerInquiryId", source = "id")
	@Mapping(target = "orderItemId", source = "orderItem.id")
	@Mapping(target = "itemName", source = "orderItem.item.name")
	@Mapping(target = "customerName", source = "user.nickName")
	GetAllCustomerInquiryForSellerResponseDto.CustomerInquiry toCustomerInquiry(CustomerInquiry customerInquiry);


	@Mapping(target = "processedAt", source = "customerInquiryReply.createdAt")
	@Mapping(target = "customerName", source = "user.username")
	@Mapping(target = "itemName", source = "orderItem.item.name")
	@Mapping(target = "orderItemId", source = "orderItem.id")
	GetCustomerInquiryForSellerResponseDto toCustomerInquiryForSeller(CustomerInquiry customerInquiry);
}
