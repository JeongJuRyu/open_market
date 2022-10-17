package com.tmax.cm.superstore.order.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.repository.OrderRepository;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public Order create(User user, Payment payment, PurchaseOrderDto purchaseOrderDto, Shipping shipping) {

        Order order = this.orderMapper.toOrder(purchaseOrderDto, payment, user, shipping);

        this.orderRepository.save(order);

        return order;
    }
}
