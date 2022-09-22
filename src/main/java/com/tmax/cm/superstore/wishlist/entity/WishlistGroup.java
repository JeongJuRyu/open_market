package com.tmax.cm.superstore.wishlist.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.tmax.cm.superstore.user.entities.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE wishlist_group SET is_deleted = true WHERE id = ?")
public class WishlistGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "wishlistGroup", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<WishlistItem> wishlistItems;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    // TODO user ID
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID", nullable = false)
//    private User user;

    public void deleteGroup() {
        for (WishlistItem wishlistItem : this.getWishlistItems()) {
            wishlistItem.setWishlistGroup(null);
        }
        wishlistItems.clear();
    }
}
