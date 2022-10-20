package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;

public interface VisitOrderRepository extends JpaRepository<VisitOrder, UUID> {

    List<VisitOrder> findBySeller(Seller seller);

    List<VisitOrder> findBySellerAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(
            Seller seller, PickupType pickupType);

    List<VisitOrder> findByOrder_User(User user);

    List<VisitOrder> findByOrder_UserAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(
            User user, PickupType pickupType);
}
