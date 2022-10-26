package com.tmax.cm.superstore.mypage.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.user.entities.User;

import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("select r from Review r where r.user.id = :userId")
    List<Review> findByUserId(UUID userId);


    List<Review> findByItem(Item item);

    @Query(value = "select r from Review r where r.createdAt >= :localDateTime")
    List<Review> findAllBySellerId(LocalDateTime localDateTime);

    @Query(value = "SELECT * FROM Review r join shipping_order_selected_option where r.shipping_order_selected_option_id = :shippingOrderSelectedOptionId", nativeQuery = true)
    Optional<Review> findByShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId);

    Optional<Review> findByPickupOrderSelectedOption(UUID pickupOrderSelectedOptionId);

    @Query(value = "SELECT * FROM REVIEW AS R JOIN ITEM AS I ON I.ID = R.ITEM_ID "
        + "JOIN SELLER AS S ON S.SELLER_ID = I.SELLER_SELLER_ID WHERE S.SELLER_ID = :sellerId", nativeQuery = true)
    List<Review> findForSellerReviewWithItem(UUID sellerId);
}
