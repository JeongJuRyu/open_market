package com.tmax.cm.superstore.seller.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.FindSellerListDto;
import com.tmax.cm.superstore.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/list")
	public ResponseEntity<ResponseDto<FindSellerListDto.Response>> findSellerList() throws Exception{
		return ResponseEntity.ok().body(sellerService.findSellerList());
	}
}
