package com.tmax.cm.superstore.mypage.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface OrderInquiryMapper {
	default GetAllOrderInquiryForSellerResponseDto.OrderInquiry toShippingOrderInquiryForSeller(
		OrderInquiry orderInquiry, Order order, Item item, User user, OrderInquiryReply orderInquiryReply){
		LocalDateTime processedAt = orderInquiryReply == null ? null : orderInquiryReply.getCreatedAt();
		return GetAllOrderInquiryForSellerResponseDto.OrderInquiry
			.builder()
			.orderId(order.getId())
			.isReplied(orderInquiry.getIsReplied())
			.content(orderInquiry.getContent())
			.CreatedAt(orderInquiry.getCreatedAt())
			.itemName(item.getName())
			.name(user.getName())
			.orderItemId(item.getId())
			.processedAt(processedAt)
			.build();
	}
	default GetAllOrderInquiryResponseDto.OrderItemInquiry toAllShippingOrderItemInquiry (OrderInquiry orderInquiry, ShippingOrderItem shippingOrderItem, Seller seller, Item item, String itemImageId){
		return GetAllOrderInquiryResponseDto.OrderItemInquiry.builder()
			.orderItemInquiryId(orderInquiry.getId())
			.shopName(seller.getSellerName())
			.orderItemName(shippingOrderItem.getName())
			.itemImageId(itemImageId)
			.orderItemId(item.getId())
			.orderType("shippingAndDelivery")
			.content(orderInquiry.getContent())
			.isReplied(orderInquiry.getIsReplied())
			.createdAt(orderInquiry.getCreatedAt())
			.build();
	}

	default GetAllOrderInquiryResponseDto.OrderItemInquiry toAllPickupOrderItemInquiry (OrderInquiry orderInquiry, PickupOrderItem pickupOrderItem, Seller seller, Item item, String itemImageId){
		return GetAllOrderInquiryResponseDto.OrderItemInquiry.builder()
			.orderItemInquiryId(orderInquiry.getId())
			.shopName(seller.getSellerName())
			.orderItemName(pickupOrderItem.getName())
			.itemImageId(itemImageId)
			.orderItemId(item.getId())
			.orderType("visitAndPickup")
			.content(orderInquiry.getContent())
			.isReplied(orderInquiry.getIsReplied())
			.createdAt(orderInquiry.getCreatedAt())
			.build();
	}

	@Mapping(target = "orderItemInquiryId", source = "orderInquiry.id")
	@Mapping(target = "shopName", source = "seller.sellerName")
	@Mapping(target = "orderItemName", source = "shippingOrderItem.name")
	@Mapping(target = "content", source = "orderInquiry.content")
	@Mapping(target = "isReplied", source = "orderInquiry.isReplied")
	@Mapping(target = "createdAt", source = "orderInquiry.createdAt")
	@Mapping(target = "orderItemId", source = "item.id")
	GetOrderInquiryResponseDto.OrderItemInquiry toShippingOrderItemInquiry (OrderInquiry orderInquiry, ShippingOrderItem shippingOrderItem, Seller seller, Item item);


	@Mapping(target = "content", source = "orderInquiryReply.content")
	@Mapping(target = "createdAt", source = "orderInquiryReply.createdAt")
	GetOrderInquiryResponseDto.OrderItemInquiryReply toShippingOrderItemInquiryReply(OrderInquiryReply orderInquiryReply);

	@Mapping(target = "orderItemInquiryId", source = "orderInquiry.id")
	@Mapping(target = "shopName", source = "seller.sellerName")
	@Mapping(target = "orderItemName", source = "pickupOrderItem.name")
	@Mapping(target = "content", source = "orderInquiry.content")
	@Mapping(target = "isReplied", source = "orderInquiry.isReplied")
	@Mapping(target = "createdAt", source = "orderInquiry.createdAt")
	@Mapping(target = "orderItemId", source = "item.id")
	GetOrderInquiryResponseDto.OrderItemInquiry toPickupOrderItemInquiry (OrderInquiry orderInquiry, PickupOrderItem pickupOrderItem, Seller seller, Item item);
}
