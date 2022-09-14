package com.tmax.cm.superstore.cart.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.repository.CartOptionRepository;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.error.exception.OptionNotFoundException;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.repository.OptionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartOptionService {

    private final OptionRepository optionRepository;
    private final CartOptionRepository cartOptionRepository;

    @Transactional
    public CartOption create(CreateCartOptionDto createCartOptionDto, CartOptionGroup cartOptionGroup) {

        Option option = this.optionRepository.findById(createCartOptionDto.getId())
                .orElseThrow(OptionNotFoundException::new);

        CartOption cartOption = CartOption.builder()
                .cartOptionGroup(cartOptionGroup)
                .count(createCartOptionDto.getCount())
                .option(option)
                .build();

        cartOptionGroup.getCartOptions().add(cartOption);

        this.cartOptionRepository.save(cartOption);

        return cartOption;
    }
}
