package com.tmax.cm.superstore.mypage.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryReplyResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateOrderInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.error.exception.OrderInquiryNotFound;
import com.tmax.cm.superstore.mypage.error.exception.ReviewNotFoundException;
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

	public ResponseDto<Object> postOrderInquiryReply(PostOrderInquiryReplyRequestDto dto) {
		OrderInquiry orderInquiry = orderInquiryRepository.findById(dto.getOrderInquiryId())
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiry.postOrderInquiryReply(dto);
		orderInquiryRepository.save(orderInquiry);
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_REPLY_CREATE)
			.data(null)
			.build();
	}

	public ResponseDto<Object> updateOrderInquiryReply(UpdateOrderInquiryReplyRequestDto dto){
		OrderInquiryReply orderInquiryReply = orderInquiryReplyRepository.findByOrderInquiryId(dto.getOrderInquiryId())
			.orElseThrow(OrderInquiryNotFound::new);
		orderInquiryReply.updateOrderInquiryReply(dto.getContent());
		orderInquiryReplyRepository.save(orderInquiryReply);
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_UPDATE)
			.data(null)
			.build();
	}

	public ResponseDto<Object> deleteOrderInquiryReply(UUID orderInquiryId){
		OrderInquiry orderInquiry = orderInquiryRepository.findByIdWithReply(orderInquiryId)
			.orElseThrow(OrderInquiryNotFound::new);
		OrderInquiryReply orderInquiryReply = orderInquiry.getOrderInquiryReply();
		orderInquiry.deleteOrderInquiryReply(orderInquiryReply);
		orderInquiryReplyRepository.delete(orderInquiryReply);
		return ResponseDto.builder()
			.responseCode(ResponseCode.ORDER_ITEM_INQUIRY_DELETE)
			.data(null)
			.build();
	}
}
