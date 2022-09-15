package com.tmax.cm.superstore.reservation.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemImageDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemOptionDto;
import com.tmax.cm.superstore.reservation.dto.FindPossibleReservationByDay;
import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.repository.*;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.error.exception.SellerAlreadyDeletedException;
import com.tmax.cm.superstore.seller.error.exception.SellerNotFoundException;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationItemRepository reservationItemRepository;
	private final ReservationItemImageRepository reservationItemImageRepository;
	private final ReservationItemOptionRepository reservationItemOptionRepository;
	private final ReservationRepository reservationRepository;
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
				.data(CreateReservationItemOptionDto.Response.builder(newReservationItemOption, findReservationItem)
					.build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindPossibleReservationByDay.Response> findPossibleReservationByDay(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			List<LocalDate> possibleReservationDate = new ArrayList<>();
			LocalDateTime currentTime = LocalDateTime.now();
			LocalDateTime afterAMonth = currentTime.plusDays(30);
			for (LocalDateTime iterDateTime = currentTime; iterDateTime.isBefore(
				afterAMonth); iterDateTime = iterDateTime.plusDays(1)) {
				for (LocalTime iterTime = findReservationItem.getStartTime(); iterTime.isBefore(
					findReservationItem.getEndTime()); iterTime = iterTime.plusHours(1)) {
					LocalDateTime checkTime = LocalDateTime.of(iterDateTime.toLocalDate(), iterTime);
					Optional<List<Reservation>> reservationCheckList = reservationRepository.findAllByReservationItemIdAndReservationTime(
						findReservationItem, checkTime);
					if (reservationCheckList.get().size()
						< findReservationItem.getAllowReservationNumberPerInterval()) {
						possibleReservationDate.add(iterDateTime.toLocalDate());
						break;
					}
				}
			}
			return ResponseDto.<FindPossibleReservationByDay.Response>builder()
				.responseCode(ResponseCode.RESERVATION_POSSIBLE_DAYS_FIND)
				.data(FindPossibleReservationByDay.Response.builder(possibleReservationDate).build())
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
