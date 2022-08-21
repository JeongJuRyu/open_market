package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entity.OrderInquiry;

public interface InquiryRepository extends JpaRepository<OrderInquiry, UUID> {
}
