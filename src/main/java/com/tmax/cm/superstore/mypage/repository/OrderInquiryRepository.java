package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entities.OrderInquiry;

public interface OrderInquiryRepository extends JpaRepository<OrderInquiry, UUID> {
}
