package com.tmax.cm.superstore.item.repository;

import java.util.List;
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

}
