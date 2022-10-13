package com.tmax.cm.superstore.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.OrderItem;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.OrderSelectedOption;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.ShippingFee;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.shop.entity.Shop;
import com.tmax.cm.superstore.user.entities.User;

@Mapper(config = CommonMapperConfig.class)
interface OrderMapper {

    default Order toOrder(PurchaseOrderDto purchaseOrderDto, Payment payment, User user) {

        Order order = Order.builder()
                .user(user)
                .payment(payment)
                .shippingOrders(null)
                .visitOrders(null)
                .deliveryOrders(null)
                .pickupOrders(null)
                .build();

        order.setShippingOrders(this.toShippingOrders(purchaseOrderDto.getShippings(), order));
        order.setVisitOrders(this.toVisitOrders(purchaseOrderDto.getVisits(), order));
        order.setDeliveryOrders(this.toDeliveryOrders(purchaseOrderDto.getDeliveries(), order));
        order.setPickupOrders(this.toPickupOrders(purchaseOrderDto.getPickups(), order));

        return order;
    }

    default List<ShippingOrder> toShippingOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order) {

        List<ShippingOrder> shippingOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            ShippingOrder shippingOrder = ShippingOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .orderItems(this.toOrderItems(entry.getValue().getCartItemDtos()))
                    .shop(entry.getKey())
                    .build();

            shippingOrders.add(shippingOrder);
        }

        return shippingOrders;
    }

    default List<VisitOrder> toVisitOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order) {

        List<VisitOrder> visitOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            VisitOrder visitOrder = VisitOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .orderItems(this.toOrderItems(entry.getValue().getCartItemDtos()))
                    .shop(entry.getKey())
                    .build();

            visitOrders.add(visitOrder);
        }

        return visitOrders;
    }

    default List<DeliveryOrder> toDeliveryOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order) {

        List<DeliveryOrder> deliveryOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .orderItems(this.toOrderItems(entry.getValue().getCartItemDtos()))
                    .shop(entry.getKey())
                    .build();

            deliveryOrders.add(deliveryOrder);
        }

        return deliveryOrders;
    }

    default List<PickupOrder> toPickupOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order) {

        List<PickupOrder> pickupOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            PickupOrder pickupOrder = PickupOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .orderItems(this.toOrderItems(entry.getValue().getCartItemDtos()))
                    .shop(entry.getKey())
                    .build();

            pickupOrders.add(pickupOrder);
        }

        return pickupOrders;
    }

    List<OrderItem> toOrderItems(List<PurchaseOrderDto.CartItemDto> cartItemDtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "cartItem")
    @Mapping(target = ".", source = "cartItem.item")
    @Mapping(target = "orderSelectedOptions", source = "selectedOptionDtos")
    OrderItem toOrderItem(PurchaseOrderDto.CartItemDto cartItemDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "selectedOption")
    @Mapping(target = "orderOptionGroups", source = "selectedOption.cartOptionGroups")
    OrderSelectedOption toOrderSelectedOption(PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "optionGroup")
    @Mapping(target = "orderOptions", source = "cartOptions")
    OrderOptionGroup toOrderOptionGroup(CartOptionGroup cartOptionGroup);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "option")
    OrderOption toOrderOptionGroup(CartOption cartOptionGroup);

    default ShippingFee toShippingFee(Integer amount) {
        return ShippingFee.builder().price(amount).build();
    }
}
