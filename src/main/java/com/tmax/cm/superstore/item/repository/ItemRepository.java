package com.tmax.cm.superstore.item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByCategoryId(Long categoryId);

    @Query("SELECT i, i.itemSendTypes, i.optionGroups FROM Item i WHERE i.name LIKE %:keyword% AND i.category.id = :categoryId ORDER BY i.createdAt ASC")
    List<Item> findByKeyword(@Param("keyword") String keyword, @Param("categoryId") Long categoryId);

    List<Item> findByNameContaining(String name);

    @Query("select i from Item i join ShippingOrderItem soi join ShippingOrderSelectedOption soso where soso.id = :id")
    Optional<Item> findByShippingOrderSelectedOption(UUID id);

    @Query("select i from Item i join PickupOrderItem poi join PickupOrderSelectedOption poso where poso.id = :id")
    Optional<Item> findByPickupOrderSelectedOption(UUID id);
}
