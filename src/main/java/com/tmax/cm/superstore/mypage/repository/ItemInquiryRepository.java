package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entities.ItemInquiry;

public interface ItemInquiryRepository extends JpaRepository<ItemInquiry, UUID> {
}
