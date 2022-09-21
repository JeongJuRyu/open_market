package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entity.ReviewReply;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, UUID> {
	ReviewReply findByReview(UUID reviewId);

}
