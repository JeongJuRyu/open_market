package com.tmax.cm.superstore.mypage.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.dto.PostCustomerInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;
import com.tmax.cm.superstore.mypage.entity.CustomerInquiryReply;
import com.tmax.cm.superstore.mypage.error.exception.CustomerInquiryNotFoundException;
import com.tmax.cm.superstore.mypage.error.exception.CustomerInquiryReplyNotFoundException;
import com.tmax.cm.superstore.mypage.repository.CustomerInquiryReplyRepository;
import com.tmax.cm.superstore.mypage.repository.CustomerInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerInquiryReplyService {
	private final CustomerInquiryRepository customerInquiryRepository;
	private final CustomerInquiryReplyRepository customerInquiryReplyRepository;

	public void postReply(PostCustomerInquiryReplyRequestDto dto){
		CustomerInquiry customerInquiry = customerInquiryRepository.findById(dto.getCustomerInquiryId())
			.orElseThrow(CustomerInquiryNotFoundException::new);

		CustomerInquiryReply customerInquiryReply =
			CustomerInquiryReply.builder()
				.customerInquiry(customerInquiry)
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		customerInquiry.updateReply(customerInquiryReply);
		customerInquiryReplyRepository.save(customerInquiryReply);
	}

	public void updateReply(UpdateCustomerInquiryReplyRequestDto dto){
		CustomerInquiry customerInquiry = customerInquiryRepository.findByIdWithReply(dto.getCustomerInquiryId())
			.orElseThrow(CustomerInquiryNotFoundException::new);
		CustomerInquiryReply customerInquiryReply = customerInquiry.getCustomerInquiryReplies().stream()
			.filter(reply -> reply.getId() == dto.getCustomerInquiryReplyId()).findFirst()
			.orElseThrow(CustomerInquiryReplyNotFoundException::new);
		customerInquiryReply.updateReply(dto);
		//인쿼리에 리플라이까지 fetch -> 해당하는 것 찾음 -> update 콜
	}

	public void deleteReply(UUID customerInquiryReplyId){
		CustomerInquiryReply customerInquiryReply = customerInquiryReplyRepository.findByIdWithInquiry(customerInquiryReplyId)
			.orElseThrow(CustomerInquiryReplyNotFoundException::new);
		customerInquiryReply.getCustomerInquiry().deleteReply(customerInquiryReply.getId());
	}
}
