package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.CartItemRepository;
import com.tmax.cm.superstore.cart.repository.CartRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.error.exception.CartItemNotFoundException;
import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.error.exception.OptionGroupNotFoundException;
import com.tmax.cm.superstore.error.exception.OptionNotFoundException;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.item.repository.OptionGroupRepository;
import com.tmax.cm.superstore.item.repository.OptionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final OptionGroupRepository optionGroupRepository;
    private final OptionRepository optionRepository;

    @Transactional
    public CartItem createCartItem(PostCartItemDto.Request request) {

        SendType sendType = request.getSendType();
        CartType selectedCartType;

        switch (sendType) {
            case SHIPPING:
                selectedCartType = CartType.SHIPPING_VISIT;
                break;
            case VISIT:
                selectedCartType = CartType.SHIPPING_VISIT;
                break;
            case DELIVERY:
                selectedCartType = CartType.DELIVERY_PICKUP;
                break;
            case PICKUP:
                selectedCartType = CartType.DELIVERY_PICKUP;
                break;
            case RESERVATION:
                selectedCartType = CartType.RESERVATION;
                break;
            default:
                selectedCartType = CartType.SHIPPING_VISIT;
                break;
        }

        Cart cart = this.cartRepository.findTopByCartType(selectedCartType);

        if (cart == null) {
            cart = Cart.builder()
                    .cartType(selectedCartType)
                    .cartItems(new ArrayList<>())
                    .build();
        }

        Item item = this.itemRepository.findById(request.getItemId()).orElseThrow(ItemNotFoundException::new);

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .selectedOptions(new ArrayList<>())
                .sendType(sendType)
                .build();

        cart.getCartItems().add(cartItem);

        for (PostCartItemDto.Request.PostSelectedOptionDto postSelectedOptionDto : request.getSelectedOptions()) {

            SelectedOption selectedOption = SelectedOption.builder()
                    .cartItem(cartItem)
                    .cartOptionGroups(new ArrayList<>())
                    .count(postSelectedOptionDto.getCount())
                    .build();

            cartItem.getSelectedOptions().add(selectedOption);

            for (PostCartItemDto.Request.PostSelectedOptionDto.PostCartOptionGroupDto postCartOptionGroupDto : postSelectedOptionDto
                    .getCartOptionGroups()) {

                OptionGroup optionGroup = this.optionGroupRepository.findById(postCartOptionGroupDto.getOptionGroupId())
                        .orElseThrow(OptionGroupNotFoundException::new);

                CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                        .selectedOption(selectedOption)
                        .cartOptions(new ArrayList<>())
                        .optionGroup(optionGroup)
                        .build();

                selectedOption.getCartOptionGroups().add(cartOptionGroup);

                for (PostCartItemDto.Request.PostSelectedOptionDto.PostCartOptionGroupDto.PostCartOptionDto postCartOptionDto : postCartOptionGroupDto
                        .getCartOpions()) {

                    Option option = this.optionRepository.findById(postCartOptionDto.getOptionId())
                            .orElseThrow(OptionNotFoundException::new);

                    CartOption cartOption = CartOption.builder()
                            .cartOptionGroup(cartOptionGroup)
                            .count(postCartOptionDto.getCount())
                            .option(option)
                            .build();

                    cartOptionGroup.getCartOptions().add(cartOption);
                }
            }
        }

        this.cartRepository.save(cart);

        return cartItem;
    }

    @Transactional
    public Cart readCart(CartType cartType) {

        return this.cartRepository.findTopByCartType(cartType);
    }

    @Transactional
    public CartItem readCartItem(UUID cartItemId) {
        
        return this.cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
    }
}
