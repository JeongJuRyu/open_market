package com.tmax.cm.superstore.mypage.mapper;

import java.util.List;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.seller.entity.Seller;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface OrderInquiryMapper {
	default GetAllOrderInquiryResponseDto.OrderItemInquiry toAllShippingOrderItemInquiry (OrderInquiry orderInquiry, ShippingOrderItem shippingOrderItem, Seller seller){
		return GetAllOrderInquiryResponseDto.OrderItemInquiry.builder()
			.id(orderInquiry.getId())
			.shopName(seller.getSellerName())
			.orderItemName(shippingOrderItem.getName())
			.content(orderInquiry.getContent())
			.isReplied(orderInquiry.getIsReplied())
			.createdAt(orderInquiry.getCreatedAt())
			.build();
	}

	default GetAllOrderInquiryResponseDto.OrderItemInquiry toPickupOrderInquiry (OrderInquiry orderInquiry, PickupOrderItem pickupOrderItem, Seller seller){
		return GetAllOrderInquiryResponseDto.OrderItemInquiry.builder()
			.id(orderInquiry.getId())
			.shopName(seller.getSellerName())
			.orderItemName(pickupOrderItem.getName())
			.content(orderInquiry.getContent())
			.isReplied(orderInquiry.getIsReplied())
			.createdAt(orderInquiry.getCreatedAt())
			.build();
	}

	@Mapping(target = "id", source = "orderInquiry.id")
	@Mapping(target = "shopName", source = "seller.sellerName")
	@Mapping(target = "orderItemName", source = "shippingOrderItem.name")
	@Mapping(target = "content", source = "orderInquiry.content")
	@Mapping(target = "isReplied", source = "orderInquiry.isReplied")
	@Mapping(target = "createdAt", source = "orderInquiry.createdAt")
	GetOrderInquiryResponseDto.OrderItemInquiry toShippingOrderItemInquiry (OrderInquiry orderInquiry, ShippingOrderItem shippingOrderItem, Seller seller);

	@Mapping(target = "content", source = "orderInquiryReply.content")
	@Mapping(target = "createdAt", source = "orderInquiryReply.createdAt")
	GetOrderInquiryResponseDto.OrderItemInquiryReply toShippingOrderItemInquiryReply(OrderInquiryReply orderInquiryReply);
}
