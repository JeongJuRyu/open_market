package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.dto.DeleteCartItemsDto;
import com.tmax.cm.superstore.cart.dto.PostCartItemDto;
import com.tmax.cm.superstore.cart.dto.PostCartReservationItemDto;
import com.tmax.cm.superstore.cart.dto.PutCartItemDto;
import com.tmax.cm.superstore.cart.dto.PutCartReservationItemDto;
import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.CartReservationItem;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.CartItemRepository;
import com.tmax.cm.superstore.cart.repository.CartRepository;
import com.tmax.cm.superstore.cart.repository.SelectedOptionRepository;
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
    private final SelectedOptionRepository selectedOptionRepository;
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

        item.validateSendType(sendType);

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
    public CartItem createCartReservationItem(PostCartReservationItemDto.Request request) {

        Cart cart = this.cartRepository.findTopByCartType(CartType.RESERVATION);

        if (cart == null) {
            cart = Cart.builder()
                    .cartType(CartType.RESERVATION)
                    .cartItems(new ArrayList<>())
                    .build();
        }

        Item item = this.itemRepository.findById(request.getItemId()).orElseThrow(ItemNotFoundException::new);

        item.validateSendType(SendType.RESERVATION);

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .selectedOptions(new ArrayList<>())
                .sendType(SendType.RESERVATION)
                .build();

        CartReservationItem cartReservationItem = CartReservationItem.builder()
                .cartItem(cartItem)
                .reservationDate(request.getReservationDate())
                .dayOfWeek(request.getDayOfWeek())
                .guestName(request.getGuestName())
                .guestPhoneNumber(request.getGuestPhoneNumber())
                .guestEmail(request.getGuestEmail())
                .reservationRequirement(request.getReservationRequirement())
                .build();

        cartItem.setCartReservationItem(cartReservationItem);

        cart.getCartItems().add(cartItem);

        // TODO 부모 자식 옵션 검증

        for (PostCartReservationItemDto.Request.PostSelectedOptionDto postSelectedOptionDto : request
                .getSelectedOptions()) {

            SelectedOption selectedOption = SelectedOption.builder()
                    .cartItem(cartItem)
                    .cartOptionGroups(new ArrayList<>())
                    .count(postSelectedOptionDto.getSelectedOptionCount())
                    .build();

            cartItem.getSelectedOptions().add(selectedOption);

            for (PostCartReservationItemDto.Request.PostSelectedOptionDto.PostCartOptionGroupDto postCartOptionGroupDto : postSelectedOptionDto
                    .getCartOptionGroups()) {

                OptionGroup optionGroup = this.optionGroupRepository.findById(postCartOptionGroupDto.getOptionGroupId())
                        .orElseThrow(OptionGroupNotFoundException::new);

                CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                        .selectedOption(selectedOption)
                        .cartOptions(new ArrayList<>())
                        .optionGroup(optionGroup)
                        .build();

                selectedOption.getCartOptionGroups().add(cartOptionGroup);

                for (PostCartReservationItemDto.Request.PostSelectedOptionDto.PostCartOptionGroupDto.PostCartOptionDto postCartOptionDto : postCartOptionGroupDto
                        .getCartOpions()) {

                    Option option = this.optionRepository.findById(postCartOptionDto.getOptionId())
                            .orElseThrow(OptionNotFoundException::new);

                    CartOption cartOption = CartOption.builder()
                            .cartOptionGroup(cartOptionGroup)
                            .count(postCartOptionDto.getCartItemOptionCount())
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

    @Transactional
    public void deleteCartItems(DeleteCartItemsDto.Request request) {
        for (UUID cartItemId : request.getCartItemIds()) {
            CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                    .orElseThrow(CartItemNotFoundException::new);

            this.cartItemRepository.delete(cartItem);
        }
    }

    @Transactional
    public void updateCartReservationItem(UUID cartItemId, PutCartReservationItemDto.Request request) {

        CartItem cartItem = this.cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);

        for (SelectedOption previousSelectedOption : cartItem.getSelectedOptions()) {
            this.selectedOptionRepository.delete(previousSelectedOption);
        }

        cartItem.getItem().validateSendType(SendType.RESERVATION);

        cartItem.getSelectedOptions().clear();

        CartReservationItem cartReservationItem = cartItem.getCartReservationItem();
        cartReservationItem.setReservationDate(request.getReservationDate());
        cartReservationItem.setDayOfWeek(request.getDayOfWeek());
        cartReservationItem.setReservationHeadcount(request.getReservationHeadcount());
        cartReservationItem.setGuestName(request.getGuestName());
        cartReservationItem.setGuestPhoneNumber(request.getGuestPhoneNumber());
        cartReservationItem.setGuestEmail(request.getGuestEmail());
        cartReservationItem.setReservationRequirement(request.getReservationRequirement());

        for (PutCartReservationItemDto.Request.PutSelectedOptionDto putSelectedOptionDto : request
                .getSelectedOptions()) {

            SelectedOption selectedOption = SelectedOption.builder()
                    .cartItem(cartItem)
                    .cartOptionGroups(new ArrayList<>())
                    .count(putSelectedOptionDto.getSelectedOptionCount())
                    .build();

            cartItem.getSelectedOptions().add(selectedOption);

            for (PutCartReservationItemDto.Request.PutSelectedOptionDto.PutCartOptionGroupDto putCartOptionGroupDto : putSelectedOptionDto
                    .getCartOptionGroups()) {

                OptionGroup optionGroup = this.optionGroupRepository.findById(putCartOptionGroupDto.getOptionGroupId())
                        .orElseThrow(OptionGroupNotFoundException::new);

                CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                        .selectedOption(selectedOption)
                        .cartOptions(new ArrayList<>())
                        .optionGroup(optionGroup)
                        .build();

                selectedOption.getCartOptionGroups().add(cartOptionGroup);

                for (PutCartReservationItemDto.Request.PutSelectedOptionDto.PutCartOptionGroupDto.PutCartOptionDto putCartOptionDto : putCartOptionGroupDto
                        .getCartOpions()) {

                    Option option = this.optionRepository.findById(putCartOptionDto.getOptionId())
                            .orElseThrow(OptionNotFoundException::new);

                    CartOption cartOption = CartOption.builder()
                            .cartOptionGroup(cartOptionGroup)
                            .count(putCartOptionDto.getCartItemOptionCount())
                            .option(option)
                            .build();

                    cartOptionGroup.getCartOptions().add(cartOption);
                }
            }
        }
    }

    @Transactional
    public void updateCartItem(UUID cartItemId, PutCartItemDto.Request request) {

        CartItem cartItem = this.cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);

        for (SelectedOption previousSelectedOption : cartItem.getSelectedOptions()) {
            this.selectedOptionRepository.delete(previousSelectedOption);
        }

        cartItem.getItem().validateSendType(request.getSendType());
        cartItem.setSendType(request.getSendType());

        cartItem.getSelectedOptions().clear();

        for (PutCartItemDto.Request.PutSelectedOptionDto putSelectedOptionDto : request.getSelectedOptions()) {

            SelectedOption selectedOption = SelectedOption.builder()
                    .cartItem(cartItem)
                    .cartOptionGroups(new ArrayList<>())
                    .count(putSelectedOptionDto.getSelectedOptionCount())
                    .build();

            cartItem.getSelectedOptions().add(selectedOption);

            for (PutCartItemDto.Request.PutSelectedOptionDto.PutCartOptionGroupDto putCartOptionGroupDto : putSelectedOptionDto
                    .getCartOptionGroups()) {

                OptionGroup optionGroup = this.optionGroupRepository.findById(putCartOptionGroupDto.getOptionGroupId())
                        .orElseThrow(OptionGroupNotFoundException::new);

                CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                        .selectedOption(selectedOption)
                        .cartOptions(new ArrayList<>())
                        .optionGroup(optionGroup)
                        .build();

                selectedOption.getCartOptionGroups().add(cartOptionGroup);

                for (PutCartItemDto.Request.PutSelectedOptionDto.PutCartOptionGroupDto.PutCartOptionDto putCartOptionDto : putCartOptionGroupDto
                        .getCartOpions()) {

                    Option option = this.optionRepository.findById(putCartOptionDto.getOptionId())
                            .orElseThrow(OptionNotFoundException::new);

                    CartOption cartOption = CartOption.builder()
                            .cartOptionGroup(cartOptionGroup)
                            .count(putCartOptionDto.getCartItemOptionCount())
                            .option(option)
                            .build();

                    cartOptionGroup.getCartOptions().add(cartOption);
                }
            }
        }
    }
}
