package com.tmax.cm.superstore.reservation.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.item.dto.FileInfo;
import com.tmax.cm.superstore.item.dto.MediaResponse;
import com.tmax.cm.superstore.item.error.exception.InternalServerErrorException;
import com.tmax.cm.superstore.reservation.dto.*;
import com.tmax.cm.superstore.reservation.entity.Reservation;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.error.exception.*;
import com.tmax.cm.superstore.reservation.repository.ReservationItemImageRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.error.exception.SellerAlreadyDeletedException;
import com.tmax.cm.superstore.seller.error.exception.SellerNotFoundException;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationItemRepository reservationItemRepository;
	private final ReservationItemImageRepository reservationItemImageRepository;
	private final ReservationItemOptionRepository reservationItemOptionRepository;
	private final ReservationRepository reservationRepository;
	private final SellerRepository sellerRepository;
	private final UserRepository userRepository;

	private final String uploadPath = "http://192.168.159.42:8888/images/upload";
	private final String uploadManyPath = "http://192.168.159.42:8888/images/upload_many";
	private final RestTemplate restTemplate;

	/**
	 * ?????? ??????
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
			findReservationItemValidation(findReservationItem);
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
			findReservationItemValidation(findReservationItem);
			findReservationItem.deleteReservationItem();
			reservationItemRepository.save(findReservationItem);

			return ResponseDto.<DeleteReservationItemDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_DELETE)
				.data(DeleteReservationItemDto.Response.builder(findReservationItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationItemsDto.Response> findReservationItems() throws Exception {
		try {
			List<ReservationItem> findReservationItemList = reservationItemRepository.findAll();
			findReservationItemValidation(findReservationItemList);
			List<FindReservationItemsDto.Response.ReservationItemList> reservationItemList = new ArrayList<>();
			ReservationItemImage findReservationItemImage = new ReservationItemImage();
			for (ReservationItem reservationItem : findReservationItemList) {
				findReservationItemImage = reservationItemImageRepository.findReservationItemImageByReservationItemId(reservationItem);
				reservationItemList.add(
					FindReservationItemsDto.Response.ReservationItemList.builder(reservationItem, findReservationItemImage).build());
			}

			return ResponseDto.<FindReservationItemsDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_ALL_FIND)
				.data(FindReservationItemsDto.Response.builder(reservationItemList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationItemDto.Response> findReservationItem(UUID reservationItemId) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);

			return ResponseDto.<FindReservationItemDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_LIST_FIND)
				.data(FindReservationItemDto.Response.builder(findReservationItem).build())
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
			findReservationItemValidation(findReservationItemList);
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
	 * ?????? ?????? ?????????
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateReservationItemImageDto.Response> createReservationItemImage(UUID reservationItemId,
		List<MultipartFile> attachment) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			if (reservationItemImageRepository.findReservationItemImageByReservationItemId(findReservationItem)
				!= null) {
				reservationItemImageRepository.delete(
					reservationItemImageRepository.findReservationItemImageByReservationItemId(findReservationItem));
			}
			List<FileInfo> fileInfos = uploadImages(attachment);
			ReservationItemImage newReservationItemImage = new ReservationItemImage();
			for(FileInfo fileInfo : fileInfos){
				newReservationItemImage = ReservationItemImage.builder(fileInfo, findReservationItem).build();
			}
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
	public ResponseDto<DeleteReservationItemImageDto.Response> deleteReservationItemImage(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);
			ReservationItemImage findReservationItemImage = reservationItemImageRepository.findReservationItemImageByReservationItemId(
				findReservationItem);
			findReservationItemImageValidation(findReservationItemImage);

			DeleteReservationItemImageDto.Response response = DeleteReservationItemImageDto.Response.builder(
				findReservationItemImage).build();
			reservationItemImageRepository.delete(findReservationItemImage);
			return ResponseDto.<DeleteReservationItemImageDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_IMAGE_DELETE)
				.data(response)
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationItemImageDto.Response> findReservationItemImage(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);
			ReservationItemImage findReservationItemImage = reservationItemImageRepository.findReservationItemImageByReservationItemId(
				findReservationItem);
			findReservationItemImageValidation(findReservationItemImage);

			return ResponseDto.<FindReservationItemImageDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_IMAGE_FIND)
				.data(FindReservationItemImageDto.Response.builder(findReservationItemImage).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ?????? ?????? ??????
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<CreateReservationItemOptionDto.Response> createReservationItemOption(UUID reservationItemId,
		CreateReservationItemOptionDto.Request createReservationItemOptionRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);

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
	public ResponseDto<ModifyReservationItemOptionDto.Response> modifyReservationItemOption(
		UUID reservationItemOptionId,
		ModifyReservationItemOptionDto.Request modifyReservationItemOptionRequestDto) throws Exception {
		try {
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				reservationItemOptionId);
			findReservationItemOptionValidation(findReservationItemOption);
			findReservationItemOption.modifyReservationItemOption(modifyReservationItemOptionRequestDto);
			reservationItemOptionRepository.save(findReservationItemOption);

			return ResponseDto.<ModifyReservationItemOptionDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_OPTION_MODIFY)
				.data(ModifyReservationItemOptionDto.Response.builder(findReservationItemOption).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<DeleteReservationItemOptionDto.Response> deleteReservationItemOption(
		UUID reservationItemOptionId) throws Exception {
		try {
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				reservationItemOptionId);
			findReservationItemOptionValidation(findReservationItemOption);
			findReservationItemOption.deleteReservationItemOption();
			reservationItemOptionRepository.save(findReservationItemOption);

			return ResponseDto.<DeleteReservationItemOptionDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_OPTION_DELETE)
				.data(DeleteReservationItemOptionDto.Response.builder(findReservationItemOption).build())
				.build();
		} catch (Exception e) {
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
			findReservationItemValidation(findReservationItem);
			List<ReservationItemOption> findReservationItemOptionList = reservationItemOptionRepository.findAllByReservationItemIdAndIsDeletedFalse(
				findReservationItem);
			findReservationItemOptionValidation(findReservationItemOptionList);
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
	 * ??????
	 */
	// ????????? ?????? ??????
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindPossibleReservationByDay.Response> findPossibleReservationByDay(UUID reservationItemId)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);
			List<LocalDate> possibleReservationDate = new ArrayList<>();
			LocalDateTime currentTime = LocalDateTime.now();
			LocalDateTime afterAMonth = currentTime.plusDays(30);
			for (LocalDateTime iterDateTime = currentTime; iterDateTime.isBefore(
				afterAMonth); iterDateTime = iterDateTime.plusDays(1)) {
				for (LocalTime iterTime = findReservationItem.getStartTime(); iterTime.isBefore(
					findReservationItem.getEndTime()); iterTime = iterTime.plusMinutes(
					Long.parseLong(findReservationItem.getReservationInterval()))) {
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

	// ????????? ?????? ??????
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindPossibleReservationByTime.Response> findPossibleReservationByTime(UUID reservationItemId,
		String reservationDay)
		throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				reservationItemId);
			findReservationItemValidation(findReservationItem);
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			LocalDate selectedReservationDay = LocalDate.parse(reservationDay, formatter);
			List<LocalTime> possibleReservationTime = new ArrayList<>();
			for (LocalTime iterTime = findReservationItem.getStartTime(); iterTime.isBefore(
				findReservationItem.getEndTime()); iterTime = iterTime.plusMinutes(
				Long.parseLong(findReservationItem.getReservationInterval()))) {
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
		User user, MakeReservationDto.Request makeReservationRequestDto) throws Exception {
		try {
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				makeReservationRequestDto.getReservationItemId());
			findReservationItemValidation(findReservationItem);
			Optional<List<Reservation>> currentReservationList = reservationRepository.findAllByReservationItemIdAndReservationTime(
				findReservationItem, makeReservationRequestDto.getReservationTime());
			if (currentReservationList.get().size() >= findReservationItem.getAllowReservationNumberPerInterval()) {
				throw new NoMoreReservationException();
			}
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				makeReservationRequestDto.getReservationItemOptionId());
			findReservationItemOptionValidation(findReservationItemOption);
			Seller findSeller = sellerRepository.findSellerBySellerId(findReservationItem.getSellerId().getSellerId());
			findSellerValidation(findSeller);
			Reservation newReservation = Reservation.builder(makeReservationRequestDto, findReservationItem,
				findReservationItemOption, findSeller, user).build();
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

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<ModifyReservationDto.Response> modifyReservation(User user, UUID reservationId,
		ModifyReservationDto.Request modifyReservationRequestDto) throws Exception {
		try {
			Optional<Reservation> findReservation = reservationRepository.findByReservationId(reservationId);
			findReservationValidation(findReservation);
			if (!user.getId().equals(findReservation.get().getUserId().getId())) {
				throw new ModifyReservationMustBeSameUserException();
			}
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				modifyReservationRequestDto.getReservationItemId());
			findReservationItemValidation(findReservationItem);
			if (!findReservationItem.getSellerId().equals(findReservation.get().getSellerId())) {
				throw new ModifyReservationMustBeSameSellerException();
			}
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				modifyReservationRequestDto.getReservationItemOptionId());
			findReservationItemOptionValidation(findReservationItemOption);
			findReservation.get()
				.modifyReservation(modifyReservationRequestDto, findReservationItem, findReservationItemOption);
			reservationRepository.save(findReservation.get());

			return ResponseDto.<ModifyReservationDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_ITEM_MODIFY)
				.data(ModifyReservationDto.Response.builder(findReservation).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationBySellerDto.Response> findReservationBySeller(UUID sellerId) throws Exception {
		try {
			Seller findSeller = sellerRepository.findSellerBySellerId(sellerId);
			findSellerValidation(findSeller);
			List<Optional<Reservation>> findReservationList = reservationRepository.findAllBySellerIdAndReservationTimeBetweenOrderByReservationTimeDesc(
				findSeller, LocalDateTime.now(), LocalDateTime.now().plusDays(30));

			List<FindReservationBySellerDto.Response.ReservationList> responseReservationList = new ArrayList<>();
			for (Optional<Reservation> reservation : findReservationList) {
				Optional<User> findUser = userRepository.findUserByEmail(reservation.get().getUserId().getEmail());
				responseReservationList.add(
					FindReservationBySellerDto.Response.ReservationList.builder(findUser, reservation).build());
			}
			return ResponseDto.<FindReservationBySellerDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_LIST_BY_SELLER_FIND)
				.data(FindReservationBySellerDto.Response.builder(responseReservationList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<FindReservationByUserDto.Response> findReservationByUser(User user) throws Exception {
		try {
			List<Optional<Reservation>> findReservationList = reservationRepository.findAllByUserIdAndReservationTimeBetweenOrderByReservationTimeDesc(
				user, LocalDateTime.now(), LocalDateTime.now().plusDays(30));

			List<FindReservationByUserDto.Response.ReservationList> responseReservationList = new ArrayList<>();
			for (Optional<Reservation> reservation : findReservationList) {
				Seller findSeller = sellerRepository.findSellerBySellerId(
					reservation.get().getSellerId().getSellerId());
				responseReservationList.add(
					FindReservationByUserDto.Response.ReservationList.builder(findSeller, reservation).build());
			}
			return ResponseDto.<FindReservationByUserDto.Response>builder()
				.responseCode(ResponseCode.RESERVATION_LIST_BY_USER_FIND)
				.data(FindReservationByUserDto.Response.builder(responseReservationList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ???????????????
	 */
	private void findSellerValidation(Seller seller) {
		if (seller == null) {
			throw new SellerNotFoundException();
		} else if (seller.isDeleted()) {
			throw new SellerAlreadyDeletedException();
		}
	}

	private void findReservationItemValidation(ReservationItem reservationItem) {
		if (reservationItem == null) {
			throw new ReservationItemNotFoundException();
		} else if (reservationItem.isDeleted()) {
			throw new ReservationItemAlreadyDeletedException();
		}
	}

	private void findReservationItemValidation(List<ReservationItem> reservationItemList) {
		if (reservationItemList.isEmpty()) {
			throw new ReservationItemListNotFoundException();
		}
	}

	private void findReservationItemImageValidation(ReservationItemImage reservationItemImage) {
		if (reservationItemImage == null) {
			throw new ReservationItemImageNotFoundException();
		}
	}

	private void findReservationItemOptionValidation(ReservationItemOption reservationItemOption) {
		if (reservationItemOption == null) {
			throw new ReservationItemOptionNotFoundException();
		} else if (reservationItemOption.isDeleted()) {
			throw new ReservationItemOptionAlreadyDeletedException();
		}
	}

	private void findReservationItemOptionValidation(List<ReservationItemOption> reservationItemOptionsList) {
		if (reservationItemOptionsList.isEmpty()) {
			throw new ReservationItemOptionListNotFoundException();
		}
	}

	private void findReservationValidation(Optional<Reservation> reservation) {
		if (reservation.isEmpty()) {
			throw new ReservationNotFoundException();
		} else if (reservation.get().getReservationTime().isBefore(LocalDateTime.now())) {
			throw new ReservationExpiredException();
		}
	}

	private List<FileInfo> uploadImages(List<MultipartFile> images){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		for (MultipartFile attachedImage : images) {
			body.add("file", attachedImage.getResource());
		}
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		if (images.size() == 1) {
			ResponseEntity<MediaResponse.Single> response = restTemplate.postForEntity(uploadPath,
				requestEntity, MediaResponse.Single.class);
			if (response.getStatusCode() != HttpStatus.OK || response.getBody().getCode() != HttpStatus.OK.value()) {
				throw new InternalServerErrorException(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
			}
			return Collections.singletonList(response.getBody().getData());
		} else {
			ResponseEntity<MediaResponse.Multi> response = restTemplate.postForEntity(
				uploadManyPath, requestEntity, MediaResponse.Multi.class);
			if (response.getStatusCode() != HttpStatus.OK || response.getBody().getCode() != HttpStatus.OK.value()) {
				throw new InternalServerErrorException(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
			}
			return response.getBody().getData();
		}
	}
}
