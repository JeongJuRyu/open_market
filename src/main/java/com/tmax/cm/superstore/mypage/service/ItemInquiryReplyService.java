package com.tmax.cm.superstore.mypage.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.mypage.dto.PostItemInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateItemInquiryReplyRequestDto;
import com.tmax.cm.superstore.mypage.entity.ItemInquiry;
import com.tmax.cm.superstore.mypage.entity.ItemInquiryReply;
import com.tmax.cm.superstore.mypage.error.exception.ItemInquiryNotFoundException;
import com.tmax.cm.superstore.mypage.error.exception.ItemInquiryReplyNotFoundException;
import com.tmax.cm.superstore.mypage.repository.ItemInquiryReplyRepository;
import com.tmax.cm.superstore.mypage.repository.ItemInquiryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemInquiryReplyService {
	private ItemInquiryRepository itemInquiryRepository;
	private ItemInquiryReplyRepository itemInquiryReplyRepository;

	public void postItemInquiryReply(PostItemInquiryReplyRequestDto dto){
		ItemInquiry itemInquiry = itemInquiryRepository.findById(dto.getItemInquiryId())
			.orElseThrow(ItemInquiryNotFoundException::new);
		ItemInquiryReply itemInquiryReply = ItemInquiryReply.builder()
			.content(dto.getContent())
			.itemInquiry(itemInquiry)
			.build();
		itemInquiry.postReply(itemInquiryReply);
	}

	public void updateItemInquiryReply(UpdateItemInquiryReplyRequestDto dto){
		ItemInquiry itemInquiry = itemInquiryRepository.findById(dto.getItemInquiryID())
			.orElseThrow(ItemInquiryNotFoundException::new);
		ItemInquiryReply itemInquiryReply = ItemInquiryReply.builder()
			.content(dto.getContent())
			.itemInquiry(itemInquiry)
			.build();
		itemInquiry.postReply(itemInquiryReply);
	}

	public void deleteItemInquiryReply(UUID itemInquiryId){
		ItemInquiry itemInquiry = itemInquiryRepository.findById(itemInquiryId)
			.orElseThrow(ItemInquiryNotFoundException::new);
		itemInquiry.removeItemInquiryReply();
	}
}
