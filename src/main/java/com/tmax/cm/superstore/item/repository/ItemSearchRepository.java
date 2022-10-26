package com.tmax.cm.superstore.item.repository;

import com.tmax.cm.superstore.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemSearchRepository extends JpaRepository<Item, UUID> {
    @Query(value = "WITH RECURSIVE cte ( id ) as ( " +
            "SELECT c.id " +
            "FROM categories c " +
            "WHERE c.parent_id = :parentCategoryId " +
            "UNION ALL " +
            "SELECT ca.id " +
            "FROM categories ca " +
            "WHERE ca.id = :parentCategoryId " +
            "UNION ALL " +
            "SELECT p.id " +
            "FROM categories p " +
            "INNER JOIN cte ON p.parent_id = cte.id " +
            ") " +
            "SELECT i.* FROM item i RIGHT JOIN cte c ON c.id = i.category_id " +
            "WHERE ( :keyword IS NULL OR i.name like %:keyword% ) " +
            "AND ( :itemState IS NULL OR i.item_state IN (:itemState) )" +
            "GROUP BY i.id " +
            "HAVING i.id IS NOT NULL ", nativeQuery = true)
    List<Item> findByKeywordAndCategoryAndItemState(@Param("keyword") String keyword, @Param("parentCategoryId") Long parentCategoryId, @Param("itemState") List<String> itemState);

    @Query(value = "WITH RECURSIVE cte ( id ) as ( " +
            "SELECT c.id " +
            "FROM categories c " +
            "WHERE c.parent_id = :parentCategoryId " +
            "UNION ALL " +
            "SELECT ca.id " +
            "FROM categories ca " +
            "WHERE ca.id = :parentCategoryId " +
            "UNION ALL " +
            "SELECT p.id " +
            "FROM categories p " +
            "INNER JOIN cte ON p.parent_id = cte.id " +
            ") " +
            "SELECT i.* FROM item i RIGHT JOIN cte c ON c.id = i.category_id " +
            "WHERE ( :keyword IS NULL OR i.name like %:keyword% ) " +
            "AND i.item_state IN (:itemState) " +
            "GROUP BY i.id " +
            "HAVING i.id IS NOT NULL ", nativeQuery = true)
    List<Item> findByKeywordAndCategoryAndItemStateList(@Param("keyword") String keyword, @Param("parentCategoryId") Long parentCategoryId, @Param("itemState") List<String> itemState);

    List<Item> findByNameContaining(String name);
}
