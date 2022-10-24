package com.tmax.cm.superstore.mypage.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.mypage.entity.OrderInquiry;
import com.tmax.cm.superstore.user.entities.enumeration.OrderType;

public interface OrderInquiryRepository extends JpaRepository<OrderInquiry, UUID> {
	@Query("select oi from OrderInquiry oi where oi.user.id = :userId "
		+ "and oi.orderType = :orderType "
		+ "and oi.createdAt >= :startDate")
	List<OrderInquiry> findAllByUserId(UUID userId, OrderType orderType, LocalDateTime startDate);
}
