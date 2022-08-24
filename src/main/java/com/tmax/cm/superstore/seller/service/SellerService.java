package com.tmax.cm.superstore.seller.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerService {

	private final SellerRepository sellerRepository;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateSellerDto.Response> createSeller(CreateSellerDto.Request createSellerRequestDto)
		throws Exception {
		try {
			Seller newSeller = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(newSeller);

			return ResponseDto.<CreateSellerDto.Response>builder()
				.responseCode(ResponseCode.SELLER_CREATE)
				.data(CreateSellerDto.Response.builder(newSeller).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
