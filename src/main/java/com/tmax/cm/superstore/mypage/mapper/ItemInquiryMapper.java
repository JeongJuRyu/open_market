package com.tmax.cm.superstore.mypage.mapper;

import java.util.List;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.GetAllItemInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetItemInquiryResponseDto;
import com.tmax.cm.superstore.mypage.entity.ItemInquiry;
import com.tmax.cm.superstore.mypage.entity.ItemInquiryAnswer;
import com.tmax.cm.superstore.mypage.entity.ItemInquiryImage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface ItemInquiryMapper {
	List<GetAllItemInquiryResponseDto.ItemInquiry> toItemInquiryDto(List<ItemInquiry> itemInquiries);

	@Mapping(target = "url", source = "url")
	GetAllItemInquiryResponseDto.ItemInquiry.ItemInquiryImage toItemInquiryImageDto(ItemInquiryImage itemInquiryImage);

	GetAllItemInquiryResponseDto.ItemInquiry.ItemInquiryAnswer toItemInquiryAnswerDto(ItemInquiryAnswer itemInquiryAnswer);
}
