package com.tmax.cm.superstore.cart.service.reservationCart;

import com.tmax.cm.superstore.cart.dto.reservationCart.*;
import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCart;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.cart.error.exception.InvalidReservationCartId;
import com.tmax.cm.superstore.cart.repository.reservationCart.CartReservationInfoRepository;
import com.tmax.cm.superstore.cart.repository.reservationCart.ReservationCartItemRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.dto.MakeReservationDto;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.error.exception.*;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import com.tmax.cm.superstore.reservation.service.ReservationService;
import com.tmax.cm.superstore.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReservationCartItemService {
	private final ReservationItemRepository reservationItemRepository;
	private final ReservationItemOptionRepository reservationItemOptionRepository;

	private final CartReservationInfoRepository cartReservationInfoRepository;
	private final ReservationCartItemRepository reservationCartItemRepository;

	private final ReservationCartService reservationCartService;
	private final ReservationService reservationService;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<PostReservationCartItemDto.Response> create(
		User user, PostReservationCartItemDto.Request postReservationCartItemRequestDto) throws Exception {
		try {
			CartType cartType = CartType.findBySendType(postReservationCartItemRequestDto.getSendType());
			ReservationCart reservationCart = reservationCartService.readReservationCart(user, cartType);
			ReservationItem reservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				postReservationCartItemRequestDto.getReservationItemId());
			findReservationItemValidation(reservationItem);
			CartReservationInfo cartReservationInfo = CartReservationInfo.builder(postReservationCartItemRequestDto)
				.build();

			cartReservationInfoRepository.save(cartReservationInfo);
			ReservationItemOption reservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				postReservationCartItemRequestDto.getReservationItemOptionId());
			findReservationItemOptionValidation(reservationItemOption);
			ReservationCartItem reservationCartItem = ReservationCartItem.builder(reservationCart, reservationItem,
				cartReservationInfo, reservationItemOption, postReservationCartItemRequestDto.getSendType()).build();
			reservationCartItemRepository.save(reservationCartItem);

			return ResponseDto.<PostReservationCartItemDto.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_CREATE)
				.data(PostReservationCartItemDto.Response.builder(reservationCartItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<GetReservationCartItemListDto.Response> findList(User user) throws Exception {
		try {
			ReservationCart reservationCart = reservationCartService.readReservationCart(user, CartType.RESERVATION);
			List<ReservationCartItem> findReservationCartItemList = reservationCartItemRepository.findAllByReservationCartIdAndIsDeletedFalse(
				reservationCart);
			List<GetReservationCartItemListDto.Response.ReservationCartList> responseList = new ArrayList<>();
			for (ReservationCartItem reservationCartItem : findReservationCartItemList) {
				responseList.add(
					GetReservationCartItemListDto.Response.ReservationCartList.builder(reservationCartItem).build());
			}

			return ResponseDto.<GetReservationCartItemListDto.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_LIST_READ)
				.data(GetReservationCartItemListDto.Response.builder(responseList).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public ResponseDto<GetReservationCartItemDto.Response> find(User user, UUID reservationCartItemId)
		throws Exception {
		try {
			ReservationCartItem findReservationCartItem = CartValidation(user, reservationCartItemId);
			return ResponseDto.<GetReservationCartItemDto.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_READ)
				.data(GetReservationCartItemDto.Response.builder(findReservationCartItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<MakeReservationDto.Response> makeReservation(User user, UUID reservationCartItemId,
		MakeReservationDto.Request makeReservationRequestDto) throws Exception {
		try {
			ReservationCartItem findReservationCartItem = CartValidation(user, reservationCartItemId);
			verifyMakeReservationRequestDto(makeReservationRequestDto, findReservationCartItem);

			ResponseDto<MakeReservationDto.Response> response = reservationService.makeReservation(user,
				makeReservationRequestDto);
			findReservationCartItem.delete();
			reservationCartItemRepository.save(findReservationCartItem);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<PatchReservationCartItem.Response> update(User user, UUID reservationCartItemId,
		PatchReservationCartItem.Request patchReservationCartItemRequestDto) throws Exception {
		try {
			ReservationCartItem findReservationCartItem = CartValidation(user, reservationCartItemId);

			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				patchReservationCartItemRequestDto.getReservationItemId());
			findReservationItemValidation(findReservationItem);
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				patchReservationCartItemRequestDto.getReservationItemOptionId());
			findReservationItemOptionValidation(findReservationItemOption);
			CartReservationInfo findCartReservationInfo = cartReservationInfoRepository.findByReservationId(
				findReservationCartItem.getCartReservationInfo().getReservationId());
			findCartReservationInfo.update(patchReservationCartItemRequestDto);
			cartReservationInfoRepository.save(findCartReservationInfo);
			findReservationCartItem.update(findReservationItem, findReservationItemOption, findCartReservationInfo);
			reservationCartItemRepository.save(findReservationCartItem);

			return ResponseDto.<PatchReservationCartItem.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_UPDATE)
				.data(PatchReservationCartItem.Response.builder(findReservationCartItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<DeleteReservationCartItem.Response> delete(User user, UUID reservationCartItemId)
		throws Exception {
		try {
			ReservationCartItem findReservationCartItem = CartValidation(user, reservationCartItemId);
			findReservationCartItem.delete();
			reservationCartItemRepository.save(findReservationCartItem);

			return ResponseDto.<DeleteReservationCartItem.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_READ)
				.data(DeleteReservationCartItem.Response.builder(findReservationCartItem).build())
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 공용 메소드
	 */
	// 장바구니의 주인과 해당 장바구니 상품의 주인과 일치한가
	private void reservationCartValidation(ReservationCart reservationCart,
		ReservationCartItem findReservationCartItem) {
		if (!reservationCart.getId().equals(findReservationCartItem.getReservationCartId().getId())) {
			throw new InvalidReservationCartId();
		}
	}

	private void findReservationItemValidation(ReservationItem reservationItem) {
		if (reservationItem == null) {
			throw new ReservationItemNotFoundException();
		} else if (reservationItem.isDeleted()) {
			throw new ReservationItemAlreadyDeletedException();
		}
	}

	private void findReservationItemOptionValidation(ReservationItemOption reservationItemOption) {
		if (reservationItemOption == null) {
			throw new ReservationItemOptionNotFoundException();
		} else if (reservationItemOption.isDeleted()) {
			throw new ReservationItemOptionAlreadyDeletedException();
		}
	}

	private ReservationCartItem CartValidation(User user, UUID reservationCartItemId) {
		ReservationCart reservationCart = reservationCartService.readReservationCart(user, CartType.RESERVATION);
		ReservationCartItem findReservationCartItem = reservationCartItemRepository.findReservationCartItemById(
			reservationCartItemId);
		reservationCartValidation(reservationCart, findReservationCartItem);
		return findReservationCartItem;
	}

	private void verifyMakeReservationRequestDto(MakeReservationDto.Request makeReservationRequestDto,
		ReservationCartItem findReservationCartItem) {
		MakeReservationDto.Request verifyRequestDto = MakeReservationDto.Request.builder()
			.reservationTime(findReservationCartItem.getCartReservationInfo().getReservationTime())
			.numberOfPeople(findReservationCartItem.getCartReservationInfo().getNumberOfPeople())
			.customerRequest(findReservationCartItem.getCartReservationInfo().getCustomerRequest())
			.reservationItemId(findReservationCartItem.getReservationItem().getReservationItemId())
			.reservationItemOptionId(findReservationCartItem.getReservationItemOption().getOptionId()).build();

		if (!verifyRequestDto.getReservationTime().equals(makeReservationRequestDto.getReservationTime())) {
			throw new InvalidMakeReservationRequestException();
		} else if (!verifyRequestDto.getNumberOfPeople().equals(makeReservationRequestDto.getNumberOfPeople())) {
			throw new InvalidMakeReservationRequestException();
		} else if (!verifyRequestDto.getCustomerRequest().equals(makeReservationRequestDto.getCustomerRequest())) {
			throw new InvalidMakeReservationRequestException();
		} else if (!verifyRequestDto.getReservationItemId().equals(makeReservationRequestDto.getReservationItemId())) {
			throw new InvalidMakeReservationRequestException();
		} else if (!verifyRequestDto.getReservationItemOptionId()
			.equals(makeReservationRequestDto.getReservationItemOptionId())) {
			throw new InvalidMakeReservationRequestException();
		}
	}

}
