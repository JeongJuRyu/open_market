package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.SelectedOptionRepository;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SelectedOptionService {

    private final SelectedOptionRepository selectedOptionRepository;

    private final CartOptionGroupService cartOptionGroupService;

    @Transactional
    public SelectedOption create(CreateSelectedOptionDto createSelectedOptionDto, CartItem cartItem) {

        SelectedOption selectedOption = SelectedOption.builder()
                .cartItem(cartItem)
                .cartOptionGroups(new ArrayList<>())
                .count(createSelectedOptionDto.getCount())
                .build();

        cartItem.getSelectedOptions().add(selectedOption);

        for (CreateCartOptionGroupDto createCartOptionGroupDto : createSelectedOptionDto.getCartOptionGroups()) {

            CartOptionGroup cartOptionGroup = this.cartOptionGroupService.create(createCartOptionGroupDto, selectedOption);

            selectedOption.getCartOptionGroups().add(cartOptionGroup);
        }

        cartItem.getSelectedOptions().add(selectedOption);

        this.selectedOptionRepository.save(selectedOption);

        return selectedOption;
    }
}
