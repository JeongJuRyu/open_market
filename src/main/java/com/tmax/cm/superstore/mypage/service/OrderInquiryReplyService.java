package com.tmax.cm.superstore.mypage.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryReplyResponseDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.mypage.mapper.OrderInquiryReplyMapper;
import com.tmax.cm.superstore.mypage.repository.OrderInquiryReplyRepository;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInquiryReplyService {
	private final OrderInquiryReplyRepository orderInquiryReplyRepository;
	private final OrderInquiryReplyMapper orderInquiryReplyMapper;

	public GetAllOrderInquiryReplyResponseDto getAllOrderInquiryReply(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<OrderInquiryReply> orderInquiryReplies = orderInquiryReplyRepository.findAllByUserId(user.getId());
		return GetAllOrderInquiryReplyResponseDto.builder()
			.orderInquiryReplies(orderInquiryReplyMapper.toOrderInquiryReplies(orderInquiryReplies))
			.build();
	}
}
