package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;

public interface PickupOrderRepository extends JpaRepository<PickupOrder, UUID> {

    List<PickupOrder> findBySeller(Seller seller);

    List<PickupOrder> findBySellerAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(
            Seller seller, PickupType pickupType);

    List<PickupOrder> findByOrder_User(User user);

    List<PickupOrder> findByOrder_UserAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(
            User user, PickupType pickupType);

    Optional<PickupOrder> findTopByPickupOrderItems(PickupOrderItem pickupOrderItem);
}
