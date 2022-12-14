package com.tmax.cm.superstore.purchaseOrder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.purchaseOrder.service.dto.mapper.PurchaseOrderDtoMapper;
import com.tmax.cm.superstore.seller.entity.Seller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PurchaseOrderService {

    private final PurchaseOrderDtoMapper purchaseOrderDtoMapper;

    @Transactional
    public PurchaseOrderDto read(List<CartItem> cartItems) {

        int totalItemAmount = 0;
        int totalShippingFee = 0;

        PurchaseOrderDto.CartItemDtosMap shippings = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap visits = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap deliveries = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap pickups = new PurchaseOrderDto.CartItemDtosMap();

        for (CartItem cartItem : cartItems) {
            Seller seller = cartItem.getItem().getSeller();
            PurchaseOrderDto.CartItemDto cartItemDto = this.purchaseOrderDtoMapper.toDto(cartItem, 3000);

            switch (cartItem.getSendType()) {
                case SHIPPING:
                    shippings.add(seller, cartItemDto);
                    break;
                case VISIT:
                    visits.add(seller, cartItemDto);
                    break;
                case DELIVERY:
                    deliveries.add(seller, cartItemDto);
                    break;
                case PICKUP:
                    pickups.add(seller, cartItemDto);
                    break;
                default:
                    break;
            }

            totalShippingFee += 3000;
            totalItemAmount += cartItemDto.getAmount();
        }

        PurchaseOrderDto.PaymentInfoDto paymentInfoDto = PurchaseOrderDto.PaymentInfoDto.builder()
                .totalItemAmount(totalItemAmount)
                .totalShippingFee(totalShippingFee)
                .totalPaymentAmount(totalItemAmount + totalShippingFee)
                .build();

        PurchaseOrderDto purchaseOrderDto = PurchaseOrderDto.builder()
                .paymentInfo(paymentInfoDto)
                .shippings(shippings)
                .visits(visits)
                .deliveries(deliveries)
                .pickups(pickups)
                .build();

        return purchaseOrderDto;
    }

    @Transactional
    public PurchaseOrderDto read(CartItem cartItem) {

        int totalItemAmount = 0;
        int totalShippingFee = 0;

        PurchaseOrderDto.CartItemDtosMap shippings = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap visits = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap deliveries = new PurchaseOrderDto.CartItemDtosMap();
        PurchaseOrderDto.CartItemDtosMap pickups = new PurchaseOrderDto.CartItemDtosMap();

        Seller seller = cartItem.getItem().getSeller();
        PurchaseOrderDto.CartItemDto cartItemDto = this.purchaseOrderDtoMapper.toDto(cartItem, 3000);

        switch (cartItem.getSendType()) {
            case SHIPPING:
                shippings.add(seller, cartItemDto);
                break;
            case VISIT:
                visits.add(seller, cartItemDto);
                break;
            case DELIVERY:
                deliveries.add(seller, cartItemDto);
                break;
            case PICKUP:
                pickups.add(seller, cartItemDto);
                break;
            default:
                break;
        }

        totalShippingFee += 3000;
        totalItemAmount += cartItemDto.getAmount();

        PurchaseOrderDto.PaymentInfoDto paymentInfoDto = PurchaseOrderDto.PaymentInfoDto.builder()
                .totalItemAmount(totalItemAmount)
                .totalShippingFee(totalShippingFee)
                .totalPaymentAmount(totalItemAmount + totalShippingFee)
                .build();

        PurchaseOrderDto purchaseOrderDto = PurchaseOrderDto.builder()
                .paymentInfo(paymentInfoDto)
                .shippings(shippings)
                .visits(visits)
                .deliveries(deliveries)
                .pickups(pickups)
                .build();

        return purchaseOrderDto;
    }
}
