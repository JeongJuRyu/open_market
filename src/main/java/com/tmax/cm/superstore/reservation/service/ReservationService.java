package com.tmax.cm.superstore.reservation.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemImageDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemOptionDto;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.repository.ReservationItemImageRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.error.exception.SellerAlreadyDeletedException;
import com.tmax.cm.superstore.seller.error.exception.SellerNotFoundException;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationItemRepository reservationItemRepository;
	private final ReservationItemImageRepository reservationItemImageRepository;
	private final ReservationItemOptionRepository reservationItemOptionRepository;
	private final SellerRepository sellerRepository;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateReservationItemDto.Response> createReservationItem(UUID sellerId,
		CreateReservationItemDto.Request createReservationItemRequestDto) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);

			ReservationItem newReservationItem = ReservationItem.builder(createReservationItemRequestDto, findSeller)
				.build();
			reservationItemRepository.save(newReservationItem);

			return ResponseDto.<CreateReservationItemDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_CREATE)
				.data(CreateReservationItemDto.Response.builder(newReservationItem, findSeller).build())
				.build();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateReservationItemImageDto.Response> createReservationItemImage(UUID reservationItemId,
		CreateReservationItemImageDto.Request createReservationItemImageRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);

			ReservationItemImage newReservationItemImage = ReservationItemImage.builder(
				createReservationItemImageRequestDto, findReservationItem).build();
			reservationItemImageRepository.save(newReservationItemImage);

			return ResponseDto.<CreateReservationItemImageDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_IMAGE_CREATE)
				.data(CreateReservationItemImageDto.Response.builder(newReservationItemImage).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateReservationItemOptionDto.Response> createReservationItemOption(UUID reservationItemId,
		CreateReservationItemOptionDto.Request createReservationItemOptionRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);

			ReservationItemOption newReservationItemOption = ReservationItemOption.builder(
				createReservationItemOptionRequestDto, findReservationItem).build();
			reservationItemOptionRepository.save(newReservationItemOption);

			return ResponseDto.<CreateReservationItemOptionDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_OPTION_CREATE)
				.data(CreateReservationItemOptionDto.Response.builder(newReservationItemOption, findReservationItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
}
