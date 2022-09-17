package com.tmax.cm.superstore.item.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.dto.GetAllOrderInquiryResponseDto;
import com.tmax.cm.superstore.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
