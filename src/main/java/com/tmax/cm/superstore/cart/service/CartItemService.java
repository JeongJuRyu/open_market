package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartReservationItem;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.CartItemRepository;
import com.tmax.cm.superstore.cart.repository.SelectedOptionRepository;
import com.tmax.cm.superstore.cart.service.dto.CreateCartItemDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartReservationItemDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;
import com.tmax.cm.superstore.cart.service.dto.DeleteCartItemsDto;
import com.tmax.cm.superstore.cart.service.dto.UpdateCartItemDto;
import com.tmax.cm.superstore.cart.service.dto.UpdateCartReservationItemDto;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.error.exception.CartItemNotFoundException;
import com.tmax.cm.superstore.error.exception.ItemNotFoundException;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final SelectedOptionRepository selectedOptionRepository;

    private final CartService cartService;
    private final SelectedOptionService selectedOptionService;

    @Transactional
    public CartItem create(CreateCartItemDto createCartItemDto) {

        CartType cartType = CartType.findBySendType(createCartItemDto.getSendType());
        Cart cart = this.cartService.readCart(cartType);

        Item item = this.itemRepository.findById(createCartItemDto.getId()).orElseThrow(ItemNotFoundException::new);

        item.validateSendType(createCartItemDto.getSendType());

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .selectedOptions(new ArrayList<>())
                .sendType(createCartItemDto.getSendType())
                .build();

        for (CreateSelectedOptionDto createSelectedOptionDto : createCartItemDto.getSelectedOptions()) {

            this.selectedOptionService.create(createSelectedOptionDto, cartItem);
        }

        cart.getCartItems().add(cartItem);

        this.cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Transactional
    public CartItem createCartReservationItem(CreateCartReservationItemDto createCartReservationItemDto) {

        Cart cart = this.cartService.readCart(CartType.RESERVATION);

        Item item = this.itemRepository.findById(createCartReservationItemDto.getItemId())
                .orElseThrow(ItemNotFoundException::new);

        item.validateSendType(SendType.RESERVATION);

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .selectedOptions(new ArrayList<>())
                .sendType(SendType.RESERVATION)
                .build();

        CartReservationItem cartReservationItem = CartReservationItem.builder()
                .cartItem(cartItem)
                .reservationDate(createCartReservationItemDto.getReservationDate())
                .dayOfWeek(createCartReservationItemDto.getDayOfWeek())
                .guestName(createCartReservationItemDto.getGuestName())
                .guestPhoneNumber(createCartReservationItemDto.getGuestPhoneNumber())
                .guestEmail(createCartReservationItemDto.getGuestEmail())
                .reservationRequirement(createCartReservationItemDto.getReservationRequirement())
                .build();

        cartItem.setCartReservationItem(cartReservationItem);

        cart.getCartItems().add(cartItem);

        // TODO 부모 자식 옵션 검증
        for (CreateSelectedOptionDto createSelectedOptionDto : createCartReservationItemDto.getSelectedOptions()) {

            SelectedOption selectedOption = this.selectedOptionService.create(createSelectedOptionDto, cartItem);

            cartItem.getSelectedOptions().add(selectedOption);
        }

        cart.getCartItems().add(cartItem);

        this.cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Transactional
    public CartItem read(UUID cartItemId) {

        return this.cartItemRepository.findById(cartItemId).orElseThrow(CartItemNotFoundException::new);
    }

    @Transactional
    public List<CartItem> read(List<UUID> cartItemIds) {
        return this.cartItemRepository.findByIdIn(cartItemIds);
    }

    @Transactional
    public void updateCartReservationItem(UUID cartItemId, UpdateCartReservationItemDto updateCartReservationItemDto) {

        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);

        for (SelectedOption previousSelectedOption : cartItem.getSelectedOptions()) {
            this.selectedOptionRepository.delete(previousSelectedOption);
        }

        cartItem.getItem().validateSendType(SendType.RESERVATION);

        cartItem.getSelectedOptions().clear();

        CartReservationItem cartReservationItem = cartItem.getCartReservationItem();
        cartReservationItem.setReservationDate(updateCartReservationItemDto.getReservationDate());
        cartReservationItem.setDayOfWeek(updateCartReservationItemDto.getDayOfWeek());
        cartReservationItem.setReservationHeadcount(updateCartReservationItemDto.getReservationHeadcount());
        cartReservationItem.setGuestName(updateCartReservationItemDto.getGuestName());
        cartReservationItem.setGuestPhoneNumber(updateCartReservationItemDto.getGuestPhoneNumber());
        cartReservationItem.setGuestEmail(updateCartReservationItemDto.getGuestEmail());
        cartReservationItem.setReservationRequirement(updateCartReservationItemDto.getReservationRequirement());

        for (CreateSelectedOptionDto createSelectedOptionDto : updateCartReservationItemDto.getSelectedOptions()) {

            SelectedOption selectedOption = this.selectedOptionService.create(createSelectedOptionDto, cartItem);

            cartItem.getSelectedOptions().add(selectedOption);
        }
    }

    @Transactional
    public void update(UUID cartItemId, UpdateCartItemDto updateCartItemDto) {

        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);

        for (SelectedOption previousSelectedOption : cartItem.getSelectedOptions()) {
            this.selectedOptionRepository.delete(previousSelectedOption);
        }

        cartItem.getItem().validateSendType(updateCartItemDto.getSendType());
        cartItem.setSendType(updateCartItemDto.getSendType());

        cartItem.getSelectedOptions().clear();

        for (CreateSelectedOptionDto createSelectedOptionDto : updateCartItemDto.getSelectedOptions()) {

            SelectedOption selectedOption = this.selectedOptionService.create(createSelectedOptionDto, cartItem);

            cartItem.getSelectedOptions().add(selectedOption);
        }
    }

    @Transactional
    public void delete(DeleteCartItemsDto deleteCartItemsServiceDto) {
        for (UUID cartItemId : deleteCartItemsServiceDto.getCartItemIds()) {
            CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                    .orElseThrow(CartItemNotFoundException::new);

            this.cartItemRepository.delete(cartItem);
        }
    }

    @Transactional
    public void delete(List<CartItem> cartItems) {
        this.cartItemRepository.deleteAll(cartItems);
    }
}
