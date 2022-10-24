package com.tmax.cm.superstore.order.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.error.exception.EntityNotFoundException;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.order.repository.DeliveryOrderRepository;
import com.tmax.cm.superstore.order.repository.OrderRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderItemRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderRepository;
import com.tmax.cm.superstore.order.repository.PickupOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderItemRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderRepository;
import com.tmax.cm.superstore.order.repository.ShippingOrderSelectedOptionRepository;
import com.tmax.cm.superstore.order.repository.VisitOrderRepository;
import com.tmax.cm.superstore.order.service.dto.ReadPickupOrderSelectedOptionDto;
import com.tmax.cm.superstore.order.service.dto.ReadShippingOrderSelectedOptionDto;
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
    private final ShippingOrderItemRepository shippingOrderItemRepository;
    private final PickupOrderItemRepository pickupOrderItemRepository;
    private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
    private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public Order create(User user, Payment payment, PurchaseOrderDto purchaseOrderDto, Shipping shippingOrderShipping,
            Pickup visitOrderPickup, Shipping deliveryOrderShipping, Pickup pickupOrderPickup) {

        Order order = this.orderMapper.toOrder(purchaseOrderDto, payment, user, shippingOrderShipping, visitOrderPickup,
                deliveryOrderShipping, pickupOrderPickup);

        this.orderRepository.save(order);

        return order;
    }

    /**
     * 상태 변경
     */
    @Transactional
    public ReadShippingOrderSelectedOptionDto readShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId,
            UUID userId) {

        Order order = null;

        ShippingOrderSelectedOption shippingOrderSelectedOption = this.shippingOrderSelectedOptionRepository
                .findById(shippingOrderSelectedOptionId)
                .orElseThrow(() -> EntityNotFoundException.of(ShippingOrderSelectedOption.class));

        ShippingOrderItem shippingOrderItem = this.shippingOrderItemRepository
                .findByShippingOrderSelectedOptions(shippingOrderSelectedOption)
                .orElseThrow(() -> EntityNotFoundException.of(ShippingOrderSelectedOption.class));

        ShippingOrder shippingOrder = this.shippingOrderRepository.findTopByShippingOrderItems(shippingOrderItem)
                .orElse(null);
        if (shippingOrder != null) {
            order = shippingOrder.getOrder();
        }
        DeliveryOrder deliveryOrder = this.deliveryOrderRepository.findTopByShippingOrderItems(shippingOrderItem)
                .orElse(null);
        if (deliveryOrder != null) {
            order = deliveryOrder.getOrder();
        }

        if (order == null || order.getUser().getId() != userId) {
            throw EntityNotFoundException.of(ShippingOrderSelectedOption.class);
        }

        boolean isReviewExist = true;
        if (shippingOrderItem.getItem().getReviews().isEmpty()) {
            isReviewExist = false;
        }

        return ReadShippingOrderSelectedOptionDto.builder().order(order).shippingOrderItem(shippingOrderItem)
                .shippingOrderSelectedOption(shippingOrderSelectedOption).isReviewExist(isReviewExist).build();
    }

    @Transactional
    public ShippingOrderSelectedOption readShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId,
            Seller seller) {
        return this.shippingOrderSelectedOptionRepository.findByIdAndSeller(shippingOrderSelectedOptionId, seller)
                .orElseThrow(() -> EntityNotFoundException.of(ShippingOrderSelectedOption.class));
    }

    @Transactional
    public ReadPickupOrderSelectedOptionDto readPickupOrderSelectedOption(UUID pickupOrderSelectedOptionId,
            UUID userId) {

        Order order = null;

        PickupOrderSelectedOption pickupOrderSelectedOption = this.pickupOrderSelectedOptionRepository
                .findById(pickupOrderSelectedOptionId)
                .orElseThrow(() -> EntityNotFoundException.of(PickupOrderSelectedOption.class));

        PickupOrderItem pickupOrderItem = this.pickupOrderItemRepository
                .findByPickupOrderSelectedOptions(pickupOrderSelectedOption)
                .orElseThrow(() -> EntityNotFoundException.of(PickupOrderSelectedOption.class));

        PickupOrder PickupOrder = this.pickupOrderRepository.findTopByPickupOrderItems(pickupOrderItem)
                .orElse(null);
        if (PickupOrder != null) {
            order = PickupOrder.getOrder();
        }
        VisitOrder visitOrder = this.visitOrderRepository.findTopByPickupOrderItems(pickupOrderItem)
                .orElse(null);
        if (visitOrder != null) {
            order = visitOrder.getOrder();
        }

        if (order == null || order.getUser().getId() != userId) {
            throw EntityNotFoundException.of(PickupOrderSelectedOption.class);
        }

        boolean isReviewExist = true;
        if (pickupOrderItem.getItem().getReviews().isEmpty()) {
            isReviewExist = false;
        }

        return ReadPickupOrderSelectedOptionDto.builder().order(order).pickupOrderItem(pickupOrderItem)
                .pickupOrderSelectedOption(pickupOrderSelectedOption).isReviewExist(isReviewExist).build();
    }

    @Transactional
    public PickupOrderSelectedOption readPickupOrderSelectedOption(UUID pickupOrderSelectedOptionId, Seller seller) {
        return this.pickupOrderSelectedOptionRepository.findByIdAndSeller(pickupOrderSelectedOptionId, seller)
                .orElseThrow(() -> EntityNotFoundException.of(PickupOrderSelectedOption.class));
    }

    /**
     * ShippingOrder
     */

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
    public List<ShippingOrder> readShippingOrders(User user) {
        return this.shippingOrderRepository.findByOrder_User(user);
    }

    @Transactional
    public List<ShippingOrder> readShippingOrdersByShippingType(User user, ShippingType shippingType) {
        return this.shippingOrderRepository
                .findByOrder_UserAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(user,
                        shippingType);
    }

    /**
     * VisitOrder
     */

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
    public List<VisitOrder> readVisitOrders(User user) {
        return this.visitOrderRepository.findByOrder_User(user);
    }

    @Transactional
    public List<VisitOrder> readVisitOrdersByPickupType(User user, PickupType pickupType) {
        return this.visitOrderRepository
                .findByOrder_UserAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(user,
                        pickupType);
    }

    /**
     * DeliveryOrder
     */

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
    public List<DeliveryOrder> readDeliveryOrders(User user) {
        return this.deliveryOrderRepository.findByOrder_User(user);
    }

    @Transactional
    public List<DeliveryOrder> readDeliveryOrdersByShippingType(User user, ShippingType shippingType) {
        return this.deliveryOrderRepository
                .findByOrder_UserAndShippingOrderItems_ShippingOrderSelectedOptions_Shipping_ShippingType(user,
                        shippingType);
    }

    /**
     * PickupOrder
     */

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

    @Transactional
    public List<PickupOrder> readPickupOrders(User user) {
        return this.pickupOrderRepository.findByOrder_User(user);
    }

    @Transactional
    public List<PickupOrder> readPickupOrdersByPickupType(User user, PickupType pickupType) {
        return this.pickupOrderRepository
                .findByOrder_UserAndPickupOrderItems_PickupOrderSelectedOptions_Pickup_PickupType(user,
                        pickupType);
    }
}
