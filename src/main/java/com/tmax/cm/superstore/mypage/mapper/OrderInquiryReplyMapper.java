package com.tmax.cm.superstore.mypage.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryReplyResponseDto;
import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;

@Mapper(config = CommonMapperConfig.class)
public interface OrderInquiryReplyMapper {
	List<GetAllOrderInquiryReplyResponseDto.OrderInquiryReply> toOrderInquiryReplies
		(List<OrderInquiryReply> orderInquiryReplies);

}
