package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.mypage.entity.ItemInquiry;

public interface ItemInquiryRepository extends JpaRepository<ItemInquiry, UUID> {
	// @Query()
	// List<ItemInquiry> findAllByUserId(UUID userId);
}
