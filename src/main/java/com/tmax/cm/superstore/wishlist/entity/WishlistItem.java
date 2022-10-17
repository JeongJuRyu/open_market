package com.tmax.cm.superstore.wishlist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE wishlist_item SET is_deleted = true WHERE id = ?")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(nullable = true)
    private WishlistGroup wishlistGroup;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    public void setGroup(WishlistGroup group) {
        if (this.wishlistGroup != null) {
            this.wishlistGroup.getWishlistItems().remove(this);
        }

        this.wishlistGroup = group;
        group.getWishlistItems().add(this);
    }
}
