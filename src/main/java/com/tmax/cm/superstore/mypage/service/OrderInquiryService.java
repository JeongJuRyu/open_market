package com.tmax.cm.superstore.mypage.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetOrderInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.error.exception.OrderInquiryNotFound;
import com.tmax.cm.superstore.mypage.mapper.OrderInquiryMapper;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryRepository;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryService {
	private final OrderInquiryRepository orderInquiryRepository;
	private final OrderInquiryMapper orderInquiryMapper;
	@Transactional(readOnly = true)
	public GetAllOrderInquiryResponseDto getAllOrderInquiry(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<OrderInquiry> orderInquiries = orderInquiryRepository.findAllByUserId(user.getId());
		return GetAllOrderInquiryResponseDto.builder()
			.orderInquiries(orderInquiryMapper.toOrderInquiries(orderInquiries))
			.build();
	}

	@Transactional(readOnly = true)
	public GetOrderInquiryResponseDto getOrderInquiry(UUID inquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(inquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		return orderInquiryMapper.toSingleOrderInquiry(orderInquiry);
	}

	@Transactional
	public UUID postOrderInquiry(PostOrderInquiryRequestDto dto){
		OrderInquiry orderInquiry = OrderInquiry.builder()
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
