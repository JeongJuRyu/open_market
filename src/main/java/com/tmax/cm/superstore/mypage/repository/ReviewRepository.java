package com.tmax.cm.superstore.mypage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entity.Review;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("select r from Review r join fetch r.reviewImages where r.user.id =: userId")
    List<Review> findAllByUserId(UUID userId);

    @Query("select r from Review r join fetch r.reviewImages join fetch r.reviewReply where r.id =: reviewId")
    Optional<Review> findReviewById(UUID reviewId);

    List<Review> findAllByItemId(UUID itemId);
}
