package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.mypage.entity.OrderInquiryReply;

public interface OrderInquiryReplyRepository extends JpaRepository<OrderInquiryReply, UUID> {
	@Query("select oir from OrderInquiryReply oir where oir.orderInquiry.user.id =: userId")
	List<OrderInquiryReply> findAllByUserId(UUID uuid);
}
