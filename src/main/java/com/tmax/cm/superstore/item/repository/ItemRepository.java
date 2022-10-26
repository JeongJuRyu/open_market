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
}
