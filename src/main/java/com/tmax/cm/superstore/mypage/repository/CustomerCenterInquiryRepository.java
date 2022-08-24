package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import com.tmax.cm.superstore.mypage.entity.CustomerCenterInquiry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerCenterInquiryRepository extends JpaRepository<CustomerCenterInquiry, UUID> {
}
