package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;

public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, UUID> {

    List<ShippingOrder> findBySeller(Seller seller);

    List<ShippingOrder> findBySellerAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(
            Seller seller, ShippingType shippingType);

    List<ShippingOrder> findByOrder_User(User user);

    List<ShippingOrder> findByOrder_UserAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(
            User user, ShippingType shippingType);

    Optional<ShippingOrder> findTopByShippingOrderItems(ShippingOrderItem shippingOrderItem);
}
