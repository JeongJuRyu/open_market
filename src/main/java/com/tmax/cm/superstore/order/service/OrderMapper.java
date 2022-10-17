package com.tmax.cm.superstore.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.order.entity.DeliveryOrder;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.PickupOrder;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.ShippingFee;
import com.tmax.cm.superstore.order.entity.ShippingOrder;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;
import com.tmax.cm.superstore.order.entity.VisitOrder;
import com.tmax.cm.superstore.payment.entity.Payment;
import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.purchaseOrder.service.dto.PurchaseOrderDto;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import com.tmax.cm.superstore.shop.entity.Shop;
import com.tmax.cm.superstore.user.entities.User;

@Mapper(config = CommonMapperConfig.class)
interface OrderMapper {

    default Order toOrder(PurchaseOrderDto purchaseOrderDto, Payment payment, User user, Shipping shippingOrderShipping,
            Pickup visitOrderPickup, Shipping deliveryOrderShipping, Pickup pickupOrderPickup) {

        Order order = Order.builder()
                .user(user)
                .payment(payment)
                .shippingOrders(null)
                .visitOrders(null)
                .deliveryOrders(null)
                .pickupOrders(null)
                .build();

        order.setShippingOrders(this.toShippingOrders(purchaseOrderDto.getShippings(), order, shippingOrderShipping));
        order.setVisitOrders(this.toVisitOrders(purchaseOrderDto.getVisits(), order, visitOrderPickup));
        order.setDeliveryOrders(this.toDeliveryOrders(purchaseOrderDto.getDeliveries(), order, deliveryOrderShipping));
        order.setPickupOrders(this.toPickupOrders(purchaseOrderDto.getPickups(), order, pickupOrderPickup));

        return order;
    }

    default List<ShippingOrder> toShippingOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order,
            Shipping shipping) {

        List<ShippingOrder> shippingOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            ShippingOrder shippingOrder = ShippingOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .shippingOrderItems(this.toShippingOrderItems(entry.getValue().getCartItemDtos(), shipping))
                    .shop(entry.getKey())
                    .build();

            shippingOrders.add(shippingOrder);
        }

        return shippingOrders;
    }

    default List<VisitOrder> toVisitOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order,
            Pickup pickup) {

        List<VisitOrder> visitOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            VisitOrder visitOrder = VisitOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .pickupOrderItems(this.toPickupOrderItems(entry.getValue().getCartItemDtos(), pickup))
                    .shop(entry.getKey())
                    .build();

            visitOrders.add(visitOrder);
        }

        return visitOrders;
    }

    default List<DeliveryOrder> toDeliveryOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order,
            Shipping shipping) {

        List<DeliveryOrder> deliveryOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .shippingOrderItems(this.toShippingOrderItems(entry.getValue().getCartItemDtos(), shipping))
                    .shop(entry.getKey())
                    .build();

            deliveryOrders.add(deliveryOrder);
        }

        return deliveryOrders;
    }

    default List<PickupOrder> toPickupOrders(PurchaseOrderDto.CartItemDtosMap cartItemDtosMap, Order order,
            Pickup pickup) {

        List<PickupOrder> pickupOrders = new ArrayList<>();

        for (Entry<Shop, PurchaseOrderDto.CartItemDtosByShop> entry : cartItemDtosMap.entrySet()) {
            PickupOrder pickupOrder = PickupOrder.builder()
                    .amount(entry.getValue().getAmount())
                    .order(order)
                    .pickupOrderItems(this.toPickupOrderItems(entry.getValue().getCartItemDtos(), pickup))
                    .shop(entry.getKey())
                    .build();

            pickupOrders.add(pickupOrder);
        }

        return pickupOrders;
    }

    default List<ShippingOrderItem> toShippingOrderItems(List<PurchaseOrderDto.CartItemDto> cartItemDtos,
            Shipping shipping) {

        List<ShippingOrderItem> shippingOrderItems = new ArrayList<>();

        for (PurchaseOrderDto.CartItemDto cartItemDto : cartItemDtos) {
            shippingOrderItems.add(this.toShippingOrderItem(cartItemDto, shipping));
        }

        return shippingOrderItems;
    }

    default List<PickupOrderItem> toPickupOrderItems(List<PurchaseOrderDto.CartItemDto> cartItemDtos, Pickup pickup) {
        List<PickupOrderItem> pickupOrderItems = new ArrayList<>();

        for (PurchaseOrderDto.CartItemDto cartItemDto : cartItemDtos) {
            pickupOrderItems.add(this.toPickupOrderItem(cartItemDto, pickup));
        }

        return pickupOrderItems;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "cartItemDto.cartItem")
    @Mapping(target = ".", source = "cartItemDto.cartItem.item")
    @Mapping(target = "pickupOrderSelectedOptions", source = "cartItemDto.selectedOptionDtos")
    PickupOrderItem toPickupOrderItem(PurchaseOrderDto.CartItemDto cartItemDto, @Context Pickup pickup);

    default List<PickupOrderSelectedOption> toSelectedOptionDtos(
            List<PurchaseOrderDto.CartItemDto.SelectedOptionDto> selectedOptionDtos, @Context Pickup pickup) {
        List<PickupOrderSelectedOption> pickupOrderSelectedOptions = new ArrayList<>();

        for (PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto : selectedOptionDtos) {
            pickupOrderSelectedOptions.add(this.toPickupOrderSelectedOption(selectedOptionDto, pickup));
        }

        return pickupOrderSelectedOptions;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "selectedOptionDto.selectedOption")
    @Mapping(target = "orderOptionGroups", source = "selectedOptionDto.selectedOption.cartOptionGroups")
    PickupOrderSelectedOption toPickupOrderSelectedOption(
            PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto, Pickup pickup);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "cartItemDto.cartItem")
    @Mapping(target = ".", source = "cartItemDto.cartItem.item")
    @Mapping(target = "shippingOrderSelectedOptions", source = "cartItemDto.selectedOptionDtos")
    ShippingOrderItem toShippingOrderItem(PurchaseOrderDto.CartItemDto cartItemDto, @Context Shipping shipping);

    default List<ShippingOrderSelectedOption> toSelectedOptionDtos(
            List<PurchaseOrderDto.CartItemDto.SelectedOptionDto> selectedOptionDtos, @Context Shipping shipping) {
        List<ShippingOrderSelectedOption> shippingOrderSelectedOptions = new ArrayList<>();

        for (PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto : selectedOptionDtos) {
            shippingOrderSelectedOptions.add(this.toShippingOrderSelectedOption(selectedOptionDto, shipping));
        }

        return shippingOrderSelectedOptions;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = ".", source = "selectedOptionDto.selectedOption")
    @Mapping(target = "orderOptionGroups", source = "selectedOptionDto.selectedOption.cartOptionGroups")
    ShippingOrderSelectedOption toShippingOrderSelectedOption(
            PurchaseOrderDto.CartItemDto.SelectedOptionDto selectedOptionDto, Shipping shipping);

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
