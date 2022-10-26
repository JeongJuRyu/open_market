package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.cart.repository.CartRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Cart createCart(User user, CartType cartType) {
        Cart cart = Cart.builder()
                .cartType(cartType)
                .cartItems(new ArrayList<>())
                .user(user)
                .build();

        this.cartRepository.save(cart);

        return cart;
    }

    @Transactional
    public Cart readCart(User user, CartType cartType) {

        Cart cart = this.cartRepository.findTopByUserAndCartType(user, cartType);

        if (cart == null) {
            cart = this.createCart(user, cartType);
        }

        return cart;
    }
}
