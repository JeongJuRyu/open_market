package com.tmax.cm.superstore.item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tmax.cm.superstore.item.code.ItemState;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findByCategoryId(Long categoryId);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %:keyword% AND i.category.id = :categoryId AND i.itemState IN (:itemState) ORDER BY i.createdAt ASC")
    List<Item> findByKeyword(@Param("keyword") String keyword, @Param("categoryId") Long categoryId, @Param("itemState") List<ItemState> itemState);

    List<Item> findByNameContaining(String name);

    @Query("select i from Item i join ShippingOrderItem soi join ShippingOrderSelectedOption soso where soso.id = :id")
    Optional<Item> findByShippingOrderSelectedOption(UUID id);

    @Query("select i from Item i join PickupOrderItem poi join PickupOrderSelectedOption poso where poso.id = :id")
    Optional<Item> findByPickupOrderSelectedOption(UUID id);
}
