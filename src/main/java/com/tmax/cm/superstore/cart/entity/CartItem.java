package com.tmax.cm.superstore.cart.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.entity.Item;

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
@SQLDelete(sql = "UPDATE cart_item SET is_deleted = true WHERE id = ?")
public class CartItem {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cart_item_cart_id"), name = "cartId", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_cart_item_item_id"), name = "itemId", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "cartItem", cascade = { CascadeType.PERSIST })
    private List<SelectedOption> selectedOptions;

    @Enumerated(EnumType.STRING)
    private SendType sendType;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;
}