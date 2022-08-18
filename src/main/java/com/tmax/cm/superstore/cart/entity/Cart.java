package com.tmax.cm.superstore.cart.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.tmax.cm.superstore.code.CartType;

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
public class Cart {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST})
	private List<CartItem> cartItems;

    // TODO user ID

    @Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private CartType cartType;
}
