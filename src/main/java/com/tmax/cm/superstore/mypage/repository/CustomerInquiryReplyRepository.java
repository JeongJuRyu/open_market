// package com.tmax.cm.superstore.mypage.repository;
//
// import java.util.Optional;
// import java.util.UUID;
//
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
//
// import com.tmax.cm.superstore.mypage.entity.CustomerInquiryReply;
//
// public interface CustomerInquiryReplyRepository extends JpaRepository<CustomerInquiryReply, UUID> {
// 	@Query("select cir from CustomerInquiryReply cir join fetch cir.customerInquiry"
// 		+ " where cir.id = :customerInquiryReplyId")
// 	Optional<CustomerInquiryReply> findByIdWithInquiry(UUID customerInquiryReplyId);
// }
