package com.tmax.cm.superstore.mypage.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.mypage.entities.Review;

public interface MyPageReviewRepository extends JpaRepository<Review, UUID> {
}
