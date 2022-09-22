package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerInquiryRepository extends JpaRepository<CustomerInquiry, UUID> {
	@Query(value = "select ci from CustomerInquiry ci join fetch ci.customerInquiryReplies "
		+ "join fetch ci.customerInquiryImages "
		+ "where ci.user.id = :userId")
	List<CustomerInquiry> findAllByUserId(UUID userId);

	@Query("select ci from CustomerInquiry ci join fetch ci.customerInquiryReplies"
		+ " where ci.id = :customerInquiryId")
	Optional<CustomerInquiry> findByIdWithReply(UUID customerInquiryId);
}
