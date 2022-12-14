package com.tmax.cm.superstore.order.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.code.ShippingType;
import com.tmax.cm.superstore.error.exception.EntityNotFoundException;
import com.tmax.cm.superstore.mypage.repository.ReviewRepository;
import com.tmax.cm.superstore.order.controller.dto.PostOrderDto;
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
import com.tmax.cm.superstore.pickup.service.PickupService;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shipping.service.ShippingService;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ShippingService shippingService;
    private final PickupService pickupService;

    private final OrderRepository orderRepository;
    private final ShippingOrderRepository shippingOrderRepository;
    private final VisitOrderRepository visitOrderRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final PickupOrderRepository pickupOrderRepository;
    private final ShippingOrderItemRepository shippingOrderItemRepository;
    private final PickupOrderItemRepository pickupOrderItemRepository;
    private final ShippingOrderSelectedOptionRepository shippingOrderSelectedOptionRepository;
    private final PickupOrderSelectedOptionRepository pickupOrderSelectedOptionRepository;
    private final ReviewRepository reviewRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public Order create(User user, Payment payment, PurchaseOrderDto purchaseOrderDto, PostOrderDto.Request request) {

        Order order = this.orderMapper.toOrder(purchaseOrderDto, payment, user, null, null, null, null);

        for (ShippingOrder shippingOrder : order.getShippingOrders()) {
            for (ShippingOrderItem shippingOrderItem : shippingOrder.getShippingOrderItems()) {
                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    Shipping shipping = this.shippingService.create(request.getShippingRecipientInfo().getRecipient(),
                            request.getShippingRecipientInfo().getAddress(),
                            request.getShippingRecipientInfo().getMobile(),
                            request.getShippingRecipientInfo().getRequests());
                    shippingOrderSelectedOption.setShipping(shipping);
                }
            }
        }
        for (VisitOrder visitOrder : order.getVisitOrders()) {
            for (PickupOrderItem pickupOrderItem : visitOrder.getPickupOrderItems()) {
                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    Pickup pickup = this.pickupService.create(request.getPickupRecipientInfo().getRecipient(),
                            request.getPickupRecipientInfo().getMobile(),
                            request.getPickupRecipientInfo().getRequests());

                    pickupOrderSelectedOption.setPickup(pickup);
                }
            }
        }
        for (DeliveryOrder deliveryOrder : order.getDeliveryOrders()) {
            for (ShippingOrderItem shippingOrderItem : deliveryOrder.getShippingOrderItems()) {
                for (ShippingOrderSelectedOption shippingOrderSelectedOption : shippingOrderItem
                        .getShippingOrderSelectedOptions()) {

                    Shipping shipping = this.shippingService.create(request.getShippingRecipientInfo().getRecipient(),
                            request.getShippingRecipientInfo().getAddress(),
                            request.getShippingRecipientInfo().getMobile(),
                            request.getShippingRecipientInfo().getRequests());
                    shippingOrderSelectedOption.setShipping(shipping);
                }
            }
        }
        for (PickupOrder pickupOrder : order.getPickupOrders()) {
            for (PickupOrderItem pickupOrderItem : pickupOrder.getPickupOrderItems()) {
                for (PickupOrderSelectedOption pickupOrderSelectedOption : pickupOrderItem
                        .getPickupOrderSelectedOptions()) {

                    Pickup pickup = this.pickupService.create(request.getPickupRecipientInfo().getRecipient(),
                            request.getPickupRecipientInfo().getMobile(),
                            request.getPickupRecipientInfo().getRequests());

                    pickupOrderSelectedOption.setPickup(pickup);
                }
            }
        }

        this.orderRepository.save(order);

        return order;
    }

    /**
     * ?????? ??????
     */
    @Transactional
    public ReadShippingOrderSelectedOptionDto readShippingOrderSelectedOption(UUID shippingOrderSelectedOptionId,
            UUID userId) {

        Order order = null;
        SendType sendType = null;

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
            sendType = SendType.SHIPPING;
        }
        DeliveryOrder deliveryOrder = this.deliveryOrderRepository.findTopByShippingOrderItems(shippingOrderItem)
                .orElse(null);
        if (deliveryOrder != null) {
            order = deliveryOrder.getOrder();
            sendType = SendType.DELIVERY;
        }

        if (order == null || order.getUser().getId() != userId) {
            throw EntityNotFoundException.of(ShippingOrderSelectedOption.class);
        }

        boolean isReviewExist = true;
        if (this.reviewRepository.findByShippingOrderSelectedOption(shippingOrderSelectedOption.getId())
                .orElse(null) == null) {
            isReviewExist = false;
        }

        return ReadShippingOrderSelectedOptionDto.builder().order(order).shippingOrderItem(shippingOrderItem)
                .shippingOrderSelectedOption(shippingOrderSelectedOption).isReviewExist(isReviewExist)
                .sendType(sendType).build();
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
        SendType sendType = null;

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
            sendType = SendType.PICKUP;
        }
        VisitOrder visitOrder = this.visitOrderRepository.findTopByPickupOrderItems(pickupOrderItem)
                .orElse(null);
        if (visitOrder != null) {
            order = visitOrder.getOrder();
            sendType = SendType.VISIT;
        }

        if (order == null || order.getUser().getId() != userId) {
            throw EntityNotFoundException.of(PickupOrderSelectedOption.class);
        }

        boolean isReviewExist = true;
        if (this.reviewRepository.findByPickupOrderSelectedOption(pickupOrderSelectedOption.getId())
                .orElse(null) == null) {
            isReviewExist = false;
        }

        return ReadPickupOrderSelectedOptionDto.builder().order(order).pickupOrderItem(pickupOrderItem)
                .pickupOrderSelectedOption(pickupOrderSelectedOption).isReviewExist(isReviewExist).sendType(sendType)
                .build();
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
