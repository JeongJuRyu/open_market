package com.tmax.cm.superstore.cart.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.CartOptionGroupRepository;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionDto;
import com.tmax.cm.superstore.cart.service.dto.CreateCartOptionGroupDto;
import com.tmax.cm.superstore.item.error.exception.OptionGroupNotFoundException;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.repository.OptionGroupRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartOptionGroupService {

    private final OptionGroupRepository optionGroupRepository;
    private final CartOptionGroupRepository cartOptionGroupRepository;

    private final CartOptionService cartOptionService;

    @Transactional
    public CartOptionGroup create(CreateCartOptionGroupDto createCartOptionGroupDto, SelectedOption selectedOption) {

        OptionGroup optionGroup = this.optionGroupRepository.findById(createCartOptionGroupDto.getId())
                .orElseThrow(OptionGroupNotFoundException::new);

        CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                .selectedOption(selectedOption)
                .cartOptions(new ArrayList<>())
                .optionGroup(optionGroup)
                .build();

        for (CreateCartOptionDto createCartOptionDto : createCartOptionGroupDto.getCartOptions()) {
            this.cartOptionService.create(createCartOptionDto, cartOptionGroup);
        }

        selectedOption.getCartOptionGroups().add(cartOptionGroup);

        this.cartOptionGroupRepository.save(cartOptionGroup);

        return cartOptionGroup;
    }
}
