package com.tmax.cm.superstore.cart.entity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class CartReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private CartItem cartItem;

    private LocalDateTime reservationDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Integer reservationHeadcount;

    @Column(length = 32)
    private String guestName;

    @Column(length = 32)
    private String guestPhoneNumber;

    @Column(length = 32)
    private String guestEmail;

    @Column(length = 64)
    private String reservationRequirement;
}
