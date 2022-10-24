package com.tmax.cm.superstore.mypage.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("select r from Review r where r.user.id = :userId "
        + "and r.createdAt >= :localDateTime "
        + "and r.isReplied = :isReplied")
    List<Review> findByUserId(UUID userId, LocalDateTime localDateTime, Boolean isReplied);

    List<Review> findAllByItemId(UUID itemId);

    @Query(value = "select r from Review r where r.createdAt >= :localDateTime")
    List<Review> findAllBySellerId(LocalDateTime localDateTime);
}
