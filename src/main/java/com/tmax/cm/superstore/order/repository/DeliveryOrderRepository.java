package com.tmax.cm.superstore.order.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, UUID> {

    List<DeliveryOrder> findBySeller(Seller seller);

    List<DeliveryOrder> findBySellerAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(
            Seller seller, ShippingType shippingType);

    List<DeliveryOrder> findByOrder_User(User user);

    List<DeliveryOrder> findByOrder_UserAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(
            User user, ShippingType shippingType);

    Optional<DeliveryOrder> findTopByShippingOrderItems(ShippingOrderItem shippingOrderItem);
}
