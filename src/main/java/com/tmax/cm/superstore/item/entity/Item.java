package com.tmax.cm.superstore.item.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.error.exception.ItemSendTypeImpossibleException;
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
public class Item {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST })
    private List<OptionGroup> optionGroups;

    @OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST })
    private List<ItemSendType> itemSendTypes;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_item_shop_id"), name = "shopId", nullable = false)
    private Shop shop;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void validateSendType(SendType sendType) {
        boolean isSendTypeContain = this.itemSendTypes.stream()
                .anyMatch((element) -> {
                    return element.getSendType() == sendType;
                });

        if (!isSendTypeContain) {
            throw new ItemSendTypeImpossibleException();
        }
    }
}
