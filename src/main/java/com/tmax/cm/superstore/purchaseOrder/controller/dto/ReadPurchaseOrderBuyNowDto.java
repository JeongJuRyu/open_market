package com.tmax.cm.superstore.purchaseOrder.controller.dto;

import java.util.List;
import java.util.UUID;

import com.tmax.cm.superstore.cart.service.dto.CreateSelectedOptionDto;
import com.tmax.cm.superstore.code.SendType;

public interface ReadPurchaseOrderBuyNowDto {

    UUID getItemId();

    SendType getSendType();

    <T extends CreateSelectedOptionDto> List<T> getSelectedOptions(); // TODO
}
