package com.tmax.cm.superstore.item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tmax.cm.superstore.item.code.ItemState;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemImage;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByCategoryId(Long categoryId);

    List<Item> findBySellerSellerId(UUID sellerId);

    @Query("select i from Item i join PickupOrderItem poi join PickupOrderSelectedOption poso where poso.id = :id")
    Optional<Item> findByPickupOrderSelectedOption(UUID id);

    @Query("select i from Item i join ItemImage ii "
        + "where ii.item.id = :itemId")
    Optional<Item> findByItemWithImage(UUID itemId);

    @Query("select i from Item i join PickupOrderItem poi join ItemImage ii "
        + "where poi.id = :pickupOrderItemId")
    Optional<Item> findByPickupOrderItem(UUID pickupOrderItemId);

    @Query(value = "select * from item as i join shipping_order_item as soi on i.id = soi.item_id "
        + "join shipping_order_selected_option as soso on soso.shipping_order_item_id = soi.id "
        + "join order_inquiry as oi on oi.shipping_order_selected_option_id = soso.id "
        + "WHERE oi.order_inquiry_id = :orderInquiryId", nativeQuery = true)
    Optional<Item> findByOrderInquiry(UUID orderInquiryId);
}
