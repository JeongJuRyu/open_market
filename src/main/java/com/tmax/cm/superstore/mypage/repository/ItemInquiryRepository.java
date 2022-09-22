package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.mypage.entity.ItemInquiry;

public interface ItemInquiryRepository extends JpaRepository<ItemInquiry, UUID> {
	@Query("select ii from ItemInquiry ii join fetch ii.itemInquiryImages where ii.user.id =:userId")
	List<ItemInquiry> findAllByUserId(UUID userId);
	@Query("select ii from ItemInquiry ii join fetch ii.itemInquiryReply "
		+ "join fetch ii.itemInquiryImages where ii.id = :itemInquiryId")
	Optional<ItemInquiry> findByIdWithReply(UUID itemInquiryId);

	Optional<ItemInquiry> findById(UUID itemInquiryReplyId);
}
