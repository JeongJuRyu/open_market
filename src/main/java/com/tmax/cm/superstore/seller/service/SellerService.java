package com.tmax.cm.superstore.seller.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.seller.dto.*;
import com.tmax.cm.superstore.seller.entity.Business;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import com.tmax.cm.superstore.seller.error.exception.*;
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
			Seller findSeller = sellerRepository.findSellerByLoginId(
				createSellerRequestDto.getSellerInfo().getLoginId());
			if (findSeller != null) {
				throw new DuplicateLoginIdException();
			}
			Seller newSeller = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(newSeller);
			Business newBusiness = Business.builder(newSeller, createSellerRequestDto).build();
			businessRepository.save(newBusiness);
			SellerDelivery newSellerDelivery = SellerDelivery.builder(newSeller, createSellerRequestDto)
				.isRepresent(true).build();
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

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<LoginSellerDto.Response> loginSeller(String loginId, String password)
		throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerByLoginId(loginId);
			if (!findSeller.getPassword().equals(password)) {
				throw new InvalidSellerPasswordException();
			}

			return ResponseDto.<LoginSellerDto.Response>builder()
				.responseCode(ResponseCode.SELLER_LOGIN)
				.data(LoginSellerDto.Response.builder(findSeller).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<DeleteSellerDto.Response> deleteSeller(UUID sellerId) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);
			findSeller.deleteSeller();
			sellerRepository.save(findSeller);

			return ResponseDto.<DeleteSellerDto.Response>builder()
				.responseCode(ResponseCode.SELLER_DELETE)
				.data(DeleteSellerDto.Response.builder(findSeller).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifySellerInfoDto.Response> modifySellerInfo(UUID sellerId,
		ModifySellerInfoDto.Request modifySellerInfoRequestDto) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSeller.modifySellerInfo(modifySellerInfoRequestDto);
			sellerRepository.save(findSeller);

			return ResponseDto.<ModifySellerInfoDto.Response>builder()
				.responseCode(ResponseCode.SELLER_INFO_MODIFY)
				.data(ModifySellerInfoDto.Response.builder(findSeller).build())
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
			findSellerValidation(findSellerList);
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

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifyBizInfoDto.Response> modifyBizInfo(UUID sellerId,
		ModifyBizInfoDto.Request modifyBizInfoRequestDto) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);
			Business findBusiness = businessRepository.findBusinessBySellerId(findSeller);
			findBusinessValidation(findBusiness);
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
			findSellerValidation(findSeller);
			Business findBusiness = businessRepository.findBusinessBySellerId(findSeller);
			findBusinessValidation(findBusiness);

			return ResponseDto.<FindBizInfo.Response>builder()
				.responseCode(ResponseCode.BUSINESS_FIND)
				.data(FindBizInfo.Response.builder(findBusiness).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateSellerDeliveryDto.Response> createSellerDelivery(UUID sellerID,
		CreateSellerDeliveryDto.Request createSellerDeliveryRequestDto) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerID);
			findSellerValidation(findSeller);
			SellerDelivery newSellerDelivery = SellerDelivery.builder(findSeller, createSellerDeliveryRequestDto)
				.build();
			sellerDeliveryRepository.save(newSellerDelivery);

			return ResponseDto.<CreateSellerDeliveryDto.Response>builder()
				.responseCode(ResponseCode.SELLER_DELIVERY_CREATE)
				.data(CreateSellerDeliveryDto.Response.builder(newSellerDelivery).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindSellerDeliveryListDto.Response> findSellerDeliveryList(UUID sellerId) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);
			List<SellerDelivery> findSellerDeliveryList = sellerDeliveryRepository.findAllBySellerId(findSeller);
			findSellerDeliveryValidation(findSellerDeliveryList);
			List<FindSellerDeliveryListDto.Response.SellerDeliveryList> responseSellerDeliveryList = new ArrayList<>();
			for (SellerDelivery findSellerDelivery : findSellerDeliveryList) {
				responseSellerDeliveryList.add(
					FindSellerDeliveryListDto.Response.SellerDeliveryList.builder(findSellerDelivery).build());
			}

			return ResponseDto.<FindSellerDeliveryListDto.Response>builder()
				.responseCode(ResponseCode.SELLER_DELIVERY_LIST_FIND)
				.data(FindSellerDeliveryListDto.Response.builder(responseSellerDeliveryList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindRepresentativeDeliveryDto.Response> findRepresentativeDelivery(UUID sellerId)
		throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);
			SellerDelivery findDelivery = sellerDeliveryRepository.findBySellerIdAndIsRepresentTrue(findSeller);
			findSellerDeliveryValidation(findDelivery);

			return ResponseDto.<FindRepresentativeDeliveryDto.Response>builder()
				.responseCode(ResponseCode.SELLER_REPRESENTATIVE_DELIVERY_FIND)
				.data(FindRepresentativeDeliveryDto.Response.builder(findDelivery).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifyRepresentativeDeliveryDto.Response> modifyRepresentativeDelivery(UUID deliveryId)
		throws Exception {
		try {
			SellerDelivery findSellerDelivery = sellerDeliveryRepository.findBySellerDeliveryId(deliveryId);
			findSellerDeliveryValidation(findSellerDelivery);
			Seller findSeller = sellerRepository.findSellerBySellerId(findSellerDelivery.getSellerId().getSellerId());
			findSellerValidation(findSeller);
			SellerDelivery findRepresentativeSellerDelivery = sellerDeliveryRepository.findBySellerIdAndIsRepresentTrue(
				findSeller);
			findSellerDeliveryValidation(findRepresentativeSellerDelivery);
			findRepresentativeSellerDelivery.modifyRepresent();
			sellerDeliveryRepository.save(findRepresentativeSellerDelivery);
			findSellerDelivery.modifyRepresent();
			sellerDeliveryRepository.save(findSellerDelivery);

			return ResponseDto.<ModifyRepresentativeDeliveryDto.Response>builder()
				.responseCode(ResponseCode.SELLER_REPRESENTATIVE_DELIVERY_MODIFY)
				.data(ModifyRepresentativeDeliveryDto.Response.builder(findSellerDelivery).build())
				.build();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/** 다른 컨트롤러에서 호출되는 메소드 */
	public Seller findSeller(UUID sellerId) {
		Seller seller = sellerRepository.findSellerBySellerId(sellerId);
		findSellerValidation(seller);

		return seller;
	}

	/**
	 * 공용메소드
	 */
	private void findSellerValidation(Seller seller) {
		if (seller == null) {
			throw new SellerNotFoundException();
		} else if (seller.isDeleted()) {
			throw new SellerAlreadyDeletedException();
		}
	}

	private void findSellerValidation(List<Seller> sellerList) {
		if (sellerList.isEmpty()) {
			throw new SellerListNotFoundException();
		}
	}

	private void findBusinessValidation(Business business) {
		if (business == null) {
			throw new BusinessNotFoundException();
		}
	}

	private void findSellerDeliveryValidation(SellerDelivery sellerDelivery) {
		if (sellerDelivery == null) {
			throw new SellerDeliveryNotFoundException();
		} else if (sellerDelivery.isDeleted()) {
			throw new SellerDeliveryAlreadyDeletedException();
		}
	}

	private void findSellerDeliveryValidation(List<SellerDelivery> sellerDeliveriesList) {
		if (sellerDeliveriesList.isEmpty()) {
			throw new SellerDeliveryListNotFoundException();
		}
	}
}
