package com.tmax.cm.superstore.mypage.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("select r from Review r where r.user.id =:userId")
    List<Review> findByUserId(UUID userId);

    List<Review> findByUser(User user);

    List<Review> findAllByItemId(UUID itemId);

    @Query(value = "select r from Review r where r.seller.sellerId =:sellerId and r.createdAt >= :localDateTime")
    List<Review> findAllBySellerId(UUID sellerId, LocalDateTime localDateTime);
}
