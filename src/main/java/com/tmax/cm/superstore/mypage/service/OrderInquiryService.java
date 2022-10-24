package com.tmax.cm.superstore.mypage.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.item.repository.OrderItemRepository;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.error.exception.OrderInquiryNotFound;
import com.tmax.cm.superstore.mypage.mapper.OrderInquiryMapper;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryRepository;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.repository.PickupOrderItemRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderItemRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderSelectedOptionRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryService {
	private final OrderInquiryRepository orderInquiryRepository;
	private final OrderItemRepository orderItemRepository;
	private final PickupOrderItemRepository pickupOrderItemRepository;
	private final ShippingOrderItemRepository shippingOrderItemRepository;
	private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
	private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
	private final SellerRepository sellerRepository;
	private final OrderInquiryMapper orderInquiryMapper;

	@Transactional(readOnly = true)
	public GetAllOrderInquiryResponseDto getAllOrderInquiry(User user, OrderType orderType, String startDate){
		List<GetAllOrderInquiryResponseDto.OrderItemInquiry> responseOrderInquiry = new ArrayList<>();
		List<OrderInquiry> orderInquiries = orderInquiryRepository.findAllByUserId(user.getId(), orderType,
			LocalDate.parse(startDate).atStartOfDay());
		for(OrderInquiry orderInquiry : orderInquiries ){
			OrderType selectedOrderType = orderInquiry.getOrderType();
			if(selectedOrderType == OrderType.SHIPPINGANDDELIVERY){
				ShippingOrderItem shippingOrderItem = shippingOrderItemRepository
					.findByShippingOrderSelectedOptions(orderInquiry.getShippingOrderSelectedOption()).get();
				Seller seller = sellerRepository.findForShippingOrderInquiry(shippingOrderItem.getId())
					.get();
				responseOrderInquiry.add(orderInquiryMapper.toAllShippingOrderItemInquiry(orderInquiry, shippingOrderItem, seller));
			} else if(selectedOrderType == OrderType.PICKUPANDVISIT){

			}
		}
		return GetAllOrderInquiryResponseDto.builder()
			.orderInquiries(responseOrderInquiry)
			.build();
	}

	@Transactional(readOnly = true)
	public GetOrderInquiryResponseDto getOrderInquiry(UUID inquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(inquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		OrderType selectedOrderType = orderInquiry.getOrderType();
		if(selectedOrderType == OrderType.SHIPPINGANDDELIVERY){
			ShippingOrderItem shippingOrderItem = shippingOrderItemRepository
				.findByShippingOrderSelectedOptions(orderInquiry.getShippingOrderSelectedOption()).get();
			Seller seller = sellerRepository.findForShippingOrderInquiry(shippingOrderItem.getId())
				.get();
			return GetOrderInquiryResponseDto.builder()
				.orderInquiry(orderInquiryMapper.toShippingOrderItemInquiry(orderInquiry, shippingOrderItem, seller))
				.orderInquiryReply(orderInquiryMapper.toShippingOrderItemInquiryReply(
					orderInquiry.getOrderInquiryReply()))
				.build();
		}
		else if(selectedOrderType == OrderType.PICKUPANDVISIT)
		{
		}
		return null;
	}

	@Transactional
	public UUID postOrderInquiry(PostOrderInquiryRequestDto dto){
		//로직 재 검증 필요
		User user = (User)SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
		PickupOrderItem orderItem = orderItemRepository.findById(dto.getSelected_option_id())
			.orElseThrow(IllegalArgumentException::new);
		OrderInquiry orderInquiry = OrderInquiry.builder()
			.content(dto.getContent())
			// .title(dto.getTitle())
			.isReplied(false)
			.user(user)
			.build();
		return orderInquiryRepository.save(orderInquiry).getId();
	}

	@Transactional
	public UUID updateOrderInquiry(UpdateOrderInquiryRequestDto dto){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(dto.getId())
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiry.updateOrderInquiry(dto);
		return dto.getId();
	}

	@Transactional
	public UUID deleteOrderInquiry(UUID inquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(inquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiryRepository.delete(orderInquiry);
		return inquiryId;
	}
}
