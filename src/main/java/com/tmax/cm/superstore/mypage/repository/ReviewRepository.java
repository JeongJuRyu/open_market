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

    @Query("select r from Review r join fetch r.reviewReply where r.id = :id")
    Optional<Review> findByIdWithReply(UUID id);

    List<Review> findByItem(Item item);

    @Query(value = "select r from Review r where r.createdAt >= :localDateTime")
    List<Review> findAllBySellerId(LocalDateTime localDateTime);

    @Query(value = "select * from review r join shipping_order_selected_option where r.shipping_order_selected_option_id = :shippingOrderSelectedOptionId", nativeQuery = true)
    Optional<Review> findByShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId);

    Optional<Review> findByPickupOrderSelectedOption(UUID pickupOrderSelectedOptionId);

    @Query(value = "select * from review as r join item as i on i.id = r.item_id "
        + "join seller as s on s.seller_id = i.seller_seller_id where s.seller_id = :sellerId", nativeQuery = true)
    List<Review> findForSellerReviewWithItem(UUID sellerId);
}
