package com.tmax.cm.superstore.seller.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.FindSellerListDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindSellerListDto.Response> findSellerList() throws Exception {
		try {
			List<Seller> findSellerList = sellerRepository.findAll();
			List<FindSellerListDto.Response.SellerList> responseSellerList = new ArrayList<>();
			for (Seller findSeller : findSellerList) {
				responseSellerList.add(FindSellerListDto.Response.SellerList.builder(findSeller).build());
			}

			return ResponseDto.<FindSellerListDto.Response>builder()
				.responseCode(ResponseCode.SELLER_LIST_FIND)
				.data(FindSellerListDto.Response.builder(responseSellerList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
