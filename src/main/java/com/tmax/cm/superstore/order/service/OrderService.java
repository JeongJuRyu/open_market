package com.tmax.cm.superstore.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.order.repository.DeliveryOrderRepository;
import com.tmax.cm.superstore.order.repository.OrderRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderRepository;
import com.tmax.cm.superstore.order.repository.VisitOrderRepository;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ShippingOrderRepository shippingOrderRepository;
    private final VisitOrderRepository visitOrderRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final PickupOrderRepository pickupOrderRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public Order create(User user, Payment payment, PurchaseOrderDto purchaseOrderDto, Shipping shippingOrderShipping,
            Pickup visitOrderPickup, Shipping deliveryOrderShipping, Pickup pickupOrderPickup) {

        Order order = this.orderMapper.toOrder(purchaseOrderDto, payment, user, shippingOrderShipping, visitOrderPickup,
                deliveryOrderShipping, pickupOrderPickup);

        this.orderRepository.save(order);

        return order;
    }

    @Transactional
    public List<ShippingOrder> readShippingOrders(Seller seller) {

        return this.shippingOrderRepository.findBySeller(seller);
    }

    @Transactional
    public List<ShippingOrder> readShippingOrdersByShippingType(Seller seller, ShippingType shippingType) {

        return this.shippingOrderRepository
                .findBySellerAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(seller,
                        shippingType);
    }

    @Transactional
    public List<VisitOrder> readVisitOrders(Seller seller) {
        return this.visitOrderRepository.findBySeller(seller);
    }

    @Transactional
    public List<VisitOrder> readVisitOrdersByPickupType(Seller seller, PickupType pickupType) {

        return this.visitOrderRepository
                .findBySellerAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(seller,
                        pickupType);
    }

    @Transactional
    public List<DeliveryOrder> readDeliveryOrders(Seller seller) {

        return this.deliveryOrderRepository.findBySeller(seller);
    }

    @Transactional
    public List<DeliveryOrder> readDeliveryOrdersByShippingType(Seller seller, ShippingType shippingType) {

        return this.deliveryOrderRepository
                .findBySellerAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(seller,
                        shippingType);
    }

    @Transactional
    public List<PickupOrder> readPickupOrders(Seller seller) {
        return this.pickupOrderRepository.findBySeller(seller);
    }

    @Transactional
    public List<PickupOrder> readPickupOrdersByPickupType(Seller seller, PickupType pickupType) {

        return this.pickupOrderRepository
                .findBySellerAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(seller,
                        pickupType);
    }
}
