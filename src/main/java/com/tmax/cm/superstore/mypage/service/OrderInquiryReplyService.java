package com.tmax.cm.superstore.mypage.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryReplyResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.mypage.error.exception.OrderInquiryNotFound;
import com.tmax.cm.superstore.mypage.mapper.OrderInquiryReplyMapper;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryReplyRepository;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryRepository;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryReplyService {
	private final OrderInquiryRepository orderInquiryRepository;
	private final OrderInquiryReplyRepository orderInquiryReplyRepository;
	private final OrderInquiryReplyMapper orderInquiryReplyMapper;

	/*public GetAllOrderInquiryReplyResponseDto getAllOrderInquiryReply(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<OrderInquiryReply> orderInquiryReplies = orderInquiryReplyRepository.findAllByUserId(user.getId());
		return GetAllOrderInquiryReplyResponseDto.builder()
			.orderInquiryReplies(orderInquiryReplyMapper.toOrderInquiryReplies(orderInquiryReplies))
			.build();
	}*/

	public void postOrderInquiryReply(PostOrderInquiryReplyRequestDto dto) {
		OrderInquiry orderInquiry = orderInquiryRepository.findById(dto.getOrderInquiryId())
			.orElseThrow(OrderInquiryNotFound::new);
		OrderInquiryReply orderInquiryReply = OrderInquiryReply.builder()
			.orderInquiry(orderInquiry)
			.content(dto.getContent()).build();
		orderInquiry.postOrderInquiryReply(orderInquiryReply);
	}

	public void updateOrderInquiryReply(UpdateOrderInquiryReplyRequestDto dto){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(dto.getOrderInquiryId())
			.orElseThrow(OrderInquiryNotFound::new);
		OrderInquiryReply orderInquiryReply = OrderInquiryReply.builder()
			.orderInquiry(orderInquiry)
			.content(dto.getContent()).build();
		orderInquiry.postOrderInquiryReply(orderInquiryReply);
	}

	public void deleteOrderInquiryReply(UUID orderInquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findById(orderInquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiry.deleteOrderInquiryReply();
	}
}
