package com.tmax.cm.superstore.reservation.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.*;
import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.error.exception.NoMoreReservationException;
import com.tmax.cm.superstore.reservation.repository.ReservationItemImageRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationRepository;
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
import java.time.format.DateTimeFormatter;
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

	/**
	 * 예약 상품
	 */
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
	public ResponseDto<ModifyReservationItemDto.Response> modifyReservationItem(UUID reservationItemId,
		ModifyReservationItemDto.Request modifyReservationItemRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItem.modifyReservationItem(modifyReservationItemRequestDto);
			reservationItemRepository.save(findReservationItem);

			return ResponseDto.<ModifyReservationItemDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_MODIFY)
				.data(ModifyReservationItemDto.Response.builder(findReservationItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<DeleteReservationItemDto.Response> deleteReservationItem(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItem.deleteReservationItem();
			reservationItemRepository.save(findReservationItem);

			return ResponseDto.<DeleteReservationItemDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_MODIFY)
				.data(DeleteReservationItemDto.Response.builder(findReservationItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationItemListDto.Response> findReservationItemList(UUID sellerId) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);

			List<ReservationItem> findReservationItemList = reservationItemRepository.findAllBySellerIdAndIsDeletedFalse(
				findSeller);
			List<FindReservationItemListDto.Response.ReservationItemList> reservationItemList = new ArrayList<>();
			for (ReservationItem reservationItem : findReservationItemList) {
				reservationItemList.add(
					FindReservationItemListDto.Response.ReservationItemList.builder(reservationItem).build());
			}

			return ResponseDto.<FindReservationItemListDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_LIST_FIND)
				.data(FindReservationItemListDto.Response.builder(reservationItemList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 예약 상품 이미지
	 */
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

	/**
	 * 예약 상품 옵션
	 */
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

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifyReservationItemOptionDto.Response> modifyReservationItemOption(UUID reservationItemOptionId,
		ModifyReservationItemOptionDto.Request modifyReservationItemOptionRequestDto) throws Exception{
		try {
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(reservationItemOptionId);
			findReservationItemOption.modifyReservationItemOption(modifyReservationItemOptionRequestDto);
			reservationItemOptionRepository.save(findReservationItemOption);

			return ResponseDto.<ModifyReservationItemOptionDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_OPTION_MODIFY)
				.data(ModifyReservationItemOptionDto.Response.builder(findReservationItemOption).build())
				.build();
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationItemOptionListDto.Response> findReservationItemOptionList(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			List<ReservationItemOption> findReservationItemOptionList = reservationItemOptionRepository.findAllByReservationItemId(
				findReservationItem);
			List<FindReservationItemOptionListDto.Response.ReservationItemOptionList> reservationItemOptionList = new ArrayList<>();
			for (ReservationItemOption reservationItemOption : findReservationItemOptionList) {
				reservationItemOptionList.add(
					FindReservationItemOptionListDto.Response.ReservationItemOptionList.builder(reservationItemOption)
						.build());
			}

			return ResponseDto.<FindReservationItemOptionListDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_OPTION_LIST_FIND)
				.data(FindReservationItemOptionListDto.Response.builder(reservationItemOptionList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 예약
	 */
	// 최적화 매우 필요
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

	// 최적화 매우 필요
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindPossibleReservationByTime.Response> findPossibleReservationByTime(UUID reservationItemId,
		String reservationDay)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			LocalDate selectedReservationDay = LocalDate.parse(reservationDay, formatter);
			List<LocalTime> possibleReservationTime = new ArrayList<>();
			for (LocalTime iterTime = findReservationItem.getStartTime(); iterTime.isBefore(
				findReservationItem.getEndTime()); iterTime = iterTime.plusHours(1)) {
				LocalDateTime checkTime = LocalDateTime.of(selectedReservationDay, iterTime);
				Optional<List<Reservation>> reservationCheckList = reservationRepository.findAllByReservationItemIdAndReservationTime(
					findReservationItem, checkTime);
				if (reservationCheckList.get().size() >= findReservationItem.getAllowReservationNumberPerInterval()) {
					continue;
				}
				possibleReservationTime.add(iterTime);
			}
			return ResponseDto.<FindPossibleReservationByTime.Response>builder()
				.responseCode(ResponseCode.RESERVATION_POSSIBLE_TIMES_FIND)
				.data(FindPossibleReservationByTime.Response.builder(possibleReservationTime).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<MakeReservationDto.Response> makeReservation(
		MakeReservationDto.Request makeReservationRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				makeReservationRequestDto.getReservationItemId());
			Optional<List<Reservation>> currentReservationList = reservationRepository.findAllByReservationItemIdAndReservationTime(
				findReservationItem, makeReservationRequestDto.getReservationTime());
			if (currentReservationList.get().size() >= findReservationItem.getAllowReservationNumberPerInterval()) {
				throw new NoMoreReservationException();
			}
			//find 예외처리 필요
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				makeReservationRequestDto.getReservationItemOptionId());
			Seller findSeller = sellerRepository.findSellerBySellerId(findReservationItem.getSellerId().getSellerId());
			Reservation newReservation = Reservation.builder(makeReservationRequestDto, findReservationItem,
				findReservationItemOption, findSeller).build();
			reservationRepository.save(newReservation);

			return ResponseDto.<MakeReservationDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_MAKE)
				.data(MakeReservationDto.Response.builder(newReservation).build())
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
