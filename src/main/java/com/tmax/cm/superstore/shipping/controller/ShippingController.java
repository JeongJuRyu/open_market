package com.tmax.cm.superstore.ship.controller;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.wishlist.dto.GetIsWishlistItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ship")
public class ShipController {

    @PostMapping("/shipment")
    public ResponseDto<Void> createShip() {
        return new ResponseDto<>(ResponseCode.SHIP_CREATE, null);
    }

//    @GetMapping("/shipment")
//    public ResponseDto<Void> getShipList() {
//
//    }
}
