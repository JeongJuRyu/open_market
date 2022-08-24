package com.tmax.cm.superstore.seller.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/seller")
public class SellerController {

	private final SellerService sellerService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<CreateSellerDto.Response>> createSeller(
		@Valid @RequestBody CreateSellerDto.Request createSellerRequestDto) throws Exception {
		return ResponseEntity.ok().body(sellerService.createSeller(createSellerRequestDto));
	}
}
