package com.tmax.cm.superstore.order.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.user.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NOTE 테이블 이름 order가 아닌 orders로 사용
 * 
 * order는 mysql에서 예약어이므로 사용 불가
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String buyerName;

    @Column(nullable = false)
    private String buyerEmail;

    @Column(nullable = false)
    private String buyerPhoneNumber;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_orders_user_id"), name = "userId", nullable = false)
    private User user;

    @OneToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_orders_payment_id"), name = "paymentId", nullable = true)
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST })
    private List<ShippingOrder> shippingOrders;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST })
    private List<VisitOrder> visitOrders;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST })
    private List<DeliveryOrder> deliveryOrders;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST })
    private List<PickupOrder> pickupOrders;
}