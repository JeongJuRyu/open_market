package com.tmax.cm.superstore.seller.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.dto.FindBizInfo;
import com.tmax.cm.superstore.seller.dto.FindSellerListDto;
import com.tmax.cm.superstore.seller.dto.ModifyBizInfoDto;
import com.tmax.cm.superstore.seller.entity.Business;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import com.tmax.cm.superstore.seller.repository.BusinessRepository;
import com.tmax.cm.superstore.seller.repository.SellerDeliveryRepository;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerService {

	private final SellerRepository sellerRepository;
	private final BusinessRepository businessRepository;
	private final SellerDeliveryRepository sellerDeliveryRepository;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateSellerDto.Response> createSeller(CreateSellerDto.Request createSellerRequestDto)
		throws Exception {
		try {
			Seller newSeller = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(newSeller);
			Business newBusiness = Business.builder(newSeller, createSellerRequestDto).build();
			businessRepository.save(newBusiness);
			SellerDelivery newSellerDelivery = SellerDelivery.builder(newSeller, createSellerRequestDto).build();
			sellerDeliveryRepository.save(newSellerDelivery);

			return ResponseDto.<CreateSellerDto.Response>builder()
				.responseCode(ResponseCode.SELLER_CREATE)
				.data(CreateSellerDto.Response.builder(newSeller, newBusiness, newSellerDelivery).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifyBizInfoDto.Response> modifyBizInfo(UUID sellerId,
		ModifyBizInfoDto.Request modifyBizInfoRequestDto) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			Business findBusiness = businessRepository.findBusinessBySellerId(findSeller);
			findBusiness.modifyBizInfo(modifyBizInfoRequestDto);
			businessRepository.save(findBusiness);

			return ResponseDto.<ModifyBizInfoDto.Response>builder()
				.responseCode(ResponseCode.BUSINESS_MODIFY)
				.data(ModifyBizInfoDto.Response.builder(findBusiness).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindBizInfo.Response> findBizInfo(UUID sellerId) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			Business findBusiness = businessRepository.findBusinessBySellerId(findSeller);

			return ResponseDto.<FindBizInfo.Response>builder()
				.responseCode(ResponseCode.BUSINESS_FIND)
				.data(FindBizInfo.Response.builder(findBusiness).build())
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
