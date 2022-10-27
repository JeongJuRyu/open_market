package com.tmax.cm.superstore.mypage.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.mypage.error.exception.OrderInquiryNotFound;
import com.tmax.cm.superstore.mypage.mapper.OrderInquiryMapper;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryReplyRepository;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryRepository;
import com.tmax.cm.superstore.mypage.util.OrderInquiryComparotor;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.repository.OrderRepository;
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
	private final OrderInquiryReplyRepository orderInquiryReplyRepository;
	private final PickupOrderItemRepository pickupOrderItemRepository;
	private final ShippingOrderItemRepository shippingOrderItemRepository;
	private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
	private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
	private final SellerRepository sellerRepository;
	private final OrderRepository orderRepository;
	private final OrderInquiryMapper orderInquiryMapper;
	private final ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public ResponseDto<GetAllOrderInquiryResponseDto> getAllOrderInquiry(User user, String startDate, String isReplied){
		List<GetAllOrderInquiryResponseDto.OrderItemInquiry> responseOrderInquiry = new ArrayList<>();
		List<OrderInquiry> orderInquiries = orderInquiryRepository.findAllByUserId(user.getId(),
			LocalDate.parse(startDate).atStartOfDay());
		if(isReplied.equals("true")){
			orderInquiries = orderInquiries.stream().filter(inquiry -> inquiry.getIsReplied()).collect(Collectors.toList());
		} else if (isReplied.equals("false")){
			orderInquiries = orderInquiries.stream().filter(inquiry -> !inquiry.getIsReplied()).collect(Collectors.toList());
		}
		orderInquiries.sort(new OrderInquiryComparotor());
		for(OrderInquiry orderInquiry : orderInquiries){
			OrderType selectedOrderType = orderInquiry.getOrderType();
			if(selectedOrderType == OrderType.SHIPPINGANDDELIVERY){
				ShippingOrderItem shippingOrderItem = shippingOrderItemRepository
					.findByShippingOrderSelectedOptions(orderInquiry.getShippingOrderSelectedOption()).get();
				Item item = shippingOrderItem.getItem();
				String itemImageId = item.getItemImages().size() == 0 ? null : item.getItemImages().get(0).getFileId();
				Seller seller = sellerRepository.findForShippingOrderInquiry(item.getId())
					.get();
				responseOrderInquiry.add(orderInquiryMapper.toAllShippingOrderItemInquiry(orderInquiry, shippingOrderItem, seller, item, itemImageId));
			} else if(selectedOrderType == OrderType.VISITANDPICKUP){
				PickupOrderItem pickupOrderItem = pickupOrderItemRepository
					.findByPickupOrderSelectedOptions(orderInquiry.getPickupOrderSelectedOption()).get();
				Item item = pickupOrderItem.getItem();
				String itemImageId = item.getItemImages().size() == 0 ? null : item.getItemImages().get(0).getFileId();
				Seller seller = sellerRepository.findForPickupOrderInquiry(item.getId())
					.get();
				responseOrderInquiry.add(orderInquiryMapper.toAllPickupOrderItemInquiry(orderInquiry, pickupOrderItem, seller, item, itemImageId));
			}
		}
		return ResponseDto.<GetAllOrderInquiryResponseDto>builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ_ALL)
			.data(
				GetAllOrderInquiryResponseDto.builder()
					.orderInquiries(responseOrderInquiry)
					.build())
			.build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetAllOrderInquiryForSellerResponseDto> getAllOrderInquiryForSeller(String startDate, String isReplied, UUID sellerId){
		List<GetAllOrderInquiryForSellerResponseDto.OrderInquiry> responseOrderInquiries = new ArrayList<>();
		List<OrderInquiry> orderInquiries = new ArrayList<>();
		List<OrderInquiry> shippingOrderInquiries = orderInquiryRepository.findForSellerOrderInquiryOfShipping(sellerId, LocalDate.parse(startDate).atStartOfDay());
		if(isReplied.equals("true")){
			shippingOrderInquiries = shippingOrderInquiries.stream().filter(inquiry -> inquiry.getIsReplied()).collect(Collectors.toList());
		} else if (isReplied.equals("false")){
			shippingOrderInquiries = shippingOrderInquiries.stream().filter(inquiry -> !inquiry.getIsReplied()).collect(Collectors.toList());
		}
		List<OrderInquiry> pickupOrderInquiries = orderInquiryRepository.findForSellerOrderInquiryOfPickup(sellerId, LocalDate.parse(startDate).atStartOfDay());
		if(isReplied.equals("true")){
			pickupOrderInquiries = shippingOrderInquiries.stream().filter(inquiry -> inquiry.getIsReplied()).collect(Collectors.toList());
		} else if (isReplied.equals("false")){
			pickupOrderInquiries = shippingOrderInquiries.stream().filter(inquiry -> !inquiry.getIsReplied()).collect(Collectors.toList());
		}
		for(OrderInquiry orderInquiry : shippingOrderInquiries) orderInquiries.add(orderInquiry);
		// for(OrderInquiry orderInquiry : pickupOrderInquiries) orderInquiries.add(orderInquiry);
		orderInquiries.sort(new OrderInquiryComparotor());
		for(OrderInquiry orderInquiry : orderInquiries){
			if(orderInquiry.getOrderType() == OrderType.SHIPPINGANDDELIVERY){
				ShippingOrderSelectedOption shippingOrderSelectedOption = orderInquiry.getShippingOrderSelectedOption();
				System.out.println(shippingOrderSelectedOption.getId());
				Order order = null;
				order = orderRepository.findByShippingSelectedOption(shippingOrderSelectedOption.getId())
					.orElse(null);
				if(order == null){
					order = orderRepository.findBySelectedOptionWIthDelivery(shippingOrderSelectedOption.getId())
						.orElseThrow(() -> new RuntimeException("주문이 없습니다."));
				}
				User user = orderInquiry.getUser();
				Item item = itemRepository.findByOrderInquiry(orderInquiry.getId()).orElseThrow(ItemNotFoundException::new);
				OrderInquiryReply orderInquiryReply = orderInquiryReplyRepository.findByOrderInquiry(orderInquiry)
						.orElse(null);
				responseOrderInquiries.add(orderInquiryMapper.toShippingOrderInquiryForSeller(orderInquiry,order,item,user,orderInquiryReply));
			}
		}
		return ResponseDto.<GetAllOrderInquiryForSellerResponseDto>builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ_ALL)
			.data(GetAllOrderInquiryForSellerResponseDto.builder()
				.orderInquiries(responseOrderInquiries)
				.build()
			).build();
	}
	@Transactional(readOnly = true)
	public ResponseDto<GetOrderInquiryForSellerResponseDto> getOrderInquiryForSeller(UUID sellerId, UUID orderInquiryId) {
		List<OrderInquiry> orderInquiries = orderInquiryRepository.findForSellerOrderInquiryOfShipping(sellerId, LocalDate.parse("1999-01-01").atStartOfDay());
		OrderInquiry orderInquiry = orderInquiries.stream().filter(oi -> oi.getId().equals(orderInquiryId)).findFirst()
			.orElseThrow(OrderInquiryNotFound::new);
		User user = orderInquiry.getUser();
		Item item = itemRepository.findByOrderInquiry(orderInquiry.getId()).orElseThrow(ItemNotFoundException::new);
		OrderInquiryReply orderInquiryReply = orderInquiryReplyRepository.findByOrderInquiry(orderInquiry)
			.orElse(null);
		String orderInquiryReplyContent = orderInquiryReply != null ? orderInquiryReply.getContent() : null;
		LocalDateTime processedAt = orderInquiryReply == null ? null : orderInquiryReply.getCreatedAt();
		if (orderInquiry.getOrderType() == OrderType.SHIPPINGANDDELIVERY) {
			ShippingOrderSelectedOption shippingOrderSelectedOption = orderInquiry.getShippingOrderSelectedOption();
			Order order = orderRepository.findByShippingSelectedOption(shippingOrderSelectedOption.getId())
				.orElseThrow(() -> new RuntimeException("주문이 없습니다."));
			return ResponseDto.<GetOrderInquiryForSellerResponseDto>builder()
				.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ)
				.data(GetOrderInquiryForSellerResponseDto.builder()
					.orderInquiry(GetOrderInquiryForSellerResponseDto.OrderInquiry
						.builder()
						.orderInquiryId(orderInquiryId)
						.orderId(order.getId())
						.isReplied(orderInquiry.getIsReplied())
						.content(orderInquiry.getContent())
						.CreatedAt(orderInquiry.getCreatedAt())
						.itemName(item.getName())
						.name(user.getName())
						.orderItemId(item.getId())
						.processedAt(processedAt)
						.build())
					.orderInquiryReply(GetOrderInquiryForSellerResponseDto.OrderInquiryReply.builder()
						.content(orderInquiryReplyContent)
						.build())
					.build())
				.build();
		}
		else if (orderInquiry.getOrderType() == OrderType.VISITANDPICKUP) {
			PickupOrderSelectedOption pickupOrderSelectedOption = orderInquiry.getPickupOrderSelectedOption();
			Order order = orderRepository.findByPickupSelectedOption(pickupOrderSelectedOption.getId())
				.orElseThrow(() -> new RuntimeException("주문이 없습니다."));
			return ResponseDto.<GetOrderInquiryForSellerResponseDto>builder()
				.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ)
				.data(GetOrderInquiryForSellerResponseDto.builder()
					.orderInquiry(GetOrderInquiryForSellerResponseDto.OrderInquiry
						.builder()
						.orderInquiryId(orderInquiryId)
						.orderId(order.getId())
						.isReplied(orderInquiry.getIsReplied())
						.content(orderInquiry.getContent())
						.CreatedAt(orderInquiry.getCreatedAt())
						.itemName(item.getName())
						.name(user.getName())
						.orderItemId(item.getId())
						.processedAt(processedAt)
						.build())
					.orderInquiryReply(GetOrderInquiryForSellerResponseDto.OrderInquiryReply.builder()
						.content(orderInquiryReplyContent)
						.build())
					.build())
				.build();
		}
		return null;
	}
	@Transactional(readOnly = true)
	public ResponseDto<GetOrderInquiryResponseDto> getShippingOrderInquiry(UUID orderInquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(orderInquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		ShippingOrderItem shippingOrderItem = shippingOrderItemRepository
			.findByShippingOrderSelectedOptions(orderInquiry.getShippingOrderSelectedOption()).get();
		Item item = shippingOrderItem.getItem();
		Seller seller = sellerRepository.findForShippingOrderInquiry(shippingOrderItem.getItem().getId())
			.get();
		OrderInquiryReply orderInquiryReply = orderInquiryReplyRepository.findByOrderInquiry(orderInquiry)
			.orElse(null);
		return ResponseDto.<GetOrderInquiryResponseDto>builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ)
			.data(
				GetOrderInquiryResponseDto.builder()
					.orderInquiry(orderInquiryMapper.toShippingOrderItemInquiry(orderInquiry, shippingOrderItem, seller, item))
					.orderInquiryReply(orderInquiryMapper.toShippingOrderItemInquiryReply(
						orderInquiryReply))
				.build())
			.build();
	}

	@Transactional(readOnly = true)
	public ResponseDto<GetOrderInquiryResponseDto> getPickupOrderInquiry(UUID orderInquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(orderInquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		PickupOrderItem pickOrderItem = pickupOrderItemRepository
			.findByPickupOrderSelectedOptions(orderInquiry.getPickupOrderSelectedOption()).get();
		Item item = pickOrderItem.getItem();
		Seller seller = sellerRepository.findForPickupOrderInquiry(pickOrderItem.getItem().getId())
			.get();
		return ResponseDto.<GetOrderInquiryResponseDto>builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_READ)
			.data(
				GetOrderInquiryResponseDto.builder()
					.orderInquiry(orderInquiryMapper.toPickupOrderItemInquiry(orderInquiry, pickOrderItem, seller, item))
					.orderInquiryReply(orderInquiryMapper.toShippingOrderItemInquiryReply(
						orderInquiry.getOrderInquiryReply()))
					.build())
			.build();
	}

	@Transactional
	public ResponseDto<Object> postOrderInquiry(PostOrderInquiryRequestDto dto, User user) throws Exception {
		OrderType orderType = dto.getOrderType();
		if(orderType == OrderType.SHIPPINGANDDELIVERY){
			ShippingOrderSelectedOption shippingOrderSelectedOption = shippingOrderSelectedOptionRepository.findById(dto.getSelectedOptionId())
				.orElseThrow(() -> new Exception("옵션이 존재하지 않습니다."));
			OrderInquiry orderInquiry = OrderInquiry.builder()
				.isReplied(false)
				.orderType(orderType)
				.shippingOrderSelectedOption(shippingOrderSelectedOption)
				.content(dto.getContent())
				.user(user)
				.build();
			orderInquiryRepository.save(orderInquiry);
		} else if (orderType == OrderType.VISITANDPICKUP){
			PickupOrderSelectedOption pickupOrderSelectedOption = pickupOrderSelectedOptionRepository.findById(dto.getSelectedOptionId())
				.orElseThrow(() -> new Exception("옵션이 존재하지 않습니다."));
			OrderInquiry orderInquiry = OrderInquiry.builder()
				.isReplied(false)
				.orderType(orderType)
				.pickupOrderSelectedOption(pickupOrderSelectedOption)
				.content(dto.getContent())
				.user(user)
				.build();
			orderInquiryRepository.save(orderInquiry);
		}
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_CREATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> updateOrderInquiry(UpdateOrderInquiryRequestDto dto){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(dto.getOrderItemInquiryId())
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiry.updateOrderInquiry(dto);
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_UPDATE)
			.data(null).build();
	}

	@Transactional
	public ResponseDto<Object> deleteOrderInquiry(UUID inquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(inquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiryRepository.delete(orderInquiry);
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_DELETE)
			.data(null).build();
	}
}
