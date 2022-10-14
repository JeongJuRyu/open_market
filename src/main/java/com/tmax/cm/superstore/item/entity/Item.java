package com.tmax.cm.superstore.item.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.item.code.ItemState;
import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.error.exception.ItemSendTypeImpossibleException;
import com.tmax.cm.superstore.shop.entity.Shop;

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
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE item SET is_deleted = true WHERE id = ?")
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<OptionGroup> optionGroups;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<ItemSendType> itemSendTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_item_shop_id"), name = "shopId", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_item_category_id"), name = "categoryId", nullable = false)
    private Category category;

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviews =new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImage> itemImages;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private ItemState itemState;

    public void validateSendType(SendType sendType) {
        boolean isSendTypeContain = this.itemSendTypes.stream()
                .anyMatch((element) -> {
                    return element.getSendType() == sendType;
                });

        if (!isSendTypeContain) {
            throw new ItemSendTypeImpossibleException();
        }
    }

    public void addItemImage(ItemImage itemImage) {
        this.itemImages.add(itemImage);
        itemImage.setItem(this);
    }

    public void updateItem(Shop shop, Category category, String name, Integer price, ItemState itemState){
        this.shop = shop;
        this.category = category;
        this.name = name;
        this.price = price;
        this.itemState = itemState;
    }

}
