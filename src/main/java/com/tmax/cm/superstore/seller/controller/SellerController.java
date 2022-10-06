package com.tmax.cm.superstore.seller.controller;

import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.FindBizInfo;
import com.tmax.cm.superstore.seller.dto.FindSellerListDto;
import com.tmax.cm.superstore.seller.dto.ModifyBizInfoDto;
import com.tmax.cm.superstore.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/seller")
public class SellerController {

	private final SellerService sellerService;

	@PostMapping
	public ResponseEntity<ResponseDto<CreateSellerDto.Response>> createSeller(
		@Valid @RequestBody CreateSellerDto.Request createSellerRequestDto) throws Exception {
		return ResponseEntity.ok().body(sellerService.createSeller(createSellerRequestDto));
	}

	@PatchMapping("/{sellerId}/business")
	public ResponseEntity<ResponseDto<ModifyBizInfoDto.Response>> modifyBizInfo(@PathVariable UUID sellerId,
		@Valid @RequestBody ModifyBizInfoDto.Request modifyBizInfoRequestDto) throws Exception {
		return ResponseEntity.ok().body(sellerService.modifyBizInfo(sellerId, modifyBizInfoRequestDto));
	}

	@GetMapping("/{sellerId}/business")
	public ResponseEntity<ResponseDto<FindBizInfo.Response>> findBizInfo(@PathVariable UUID sellerId) throws Exception {
		return ResponseEntity.ok().body(sellerService.findBizInfo(sellerId));
	}

	@GetMapping("/list")
	public ResponseEntity<ResponseDto<FindSellerListDto.Response>> findSellerList() throws Exception {
		return ResponseEntity.ok().body(sellerService.findSellerList());
	}
}
