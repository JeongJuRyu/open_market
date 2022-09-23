package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerInquiryRepository extends JpaRepository<CustomerInquiry, UUID> {
	@Query(value = "select ci from CustomerInquiry ci left join customerInquiry "
		// + "left join ci.customerInquiryImages "
		+ "where ci.user.id = :userId", nativeQuery = true)
	List<CustomerInquiry> findAllByUserId(UUID userId);

	@Query("select ci from CustomerInquiry ci join fetch ci.customerInquiryReply "
		+ "where ci.id = :customerInquiryId")
	Optional<CustomerInquiry> findByIdWithReply(UUID customerInquiryId);

	List<CustomerInquiry> findBySeller(UUID sellerId);

	@Query(value = "SELECT * FROM CustomerInquiry JOIN USER ON :customerInquiryId = USER.ID "
		+ "JOIN ORDER_ITEM ON :customerInquiryId = ORDER_ITEM.ID "
		+ "JOIN ITEM ON ORDER_ITEM.ID = ITEM.ID"
		+ "WHERE CustomerInquiry.ID = :customerInquiryId" , nativeQuery = true)
	Optional<CustomerInquiry> findByIdForSeller(UUID customerInquiryId);

}
