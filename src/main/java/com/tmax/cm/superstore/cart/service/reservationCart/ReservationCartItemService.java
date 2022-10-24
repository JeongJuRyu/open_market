package com.tmax.cm.superstore.cart.service.reservationCart;

import com.tmax.cm.superstore.cart.dto.reservationCart.*;
import com.tmax.cm.superstore.cart.entity.reservationCart.CartReservationInfo;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCart;
import com.tmax.cm.superstore.cart.entity.reservationCart.ReservationCartItem;
import com.tmax.cm.superstore.cart.repository.reservationCart.CartReservationInfoRepository;
import com.tmax.cm.superstore.cart.repository.reservationCart.ReservationCartItemRepository;
import com.tmax.cm.superstore.cart.repository.reservationCart.ReservationCartRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
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

	private final ReservationCartRepository reservationCartRepository;
	private final CartReservationInfoRepository cartReservationInfoRepository;
	private final ReservationCartItemRepository reservationCartItemRepository;

	private final ReservationCartService reservationCartService;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<PostReservationCartItemDto.Response> create(
		User user, PostReservationCartItemDto.Request postReservationCartItemRequestDto) throws Exception {
		try {
			CartType cartType = CartType.findBySendType(postReservationCartItemRequestDto.getSendType());
			ReservationCart reservationCart = reservationCartService.readReservationCart(user, cartType);
			ReservationItem reservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				postReservationCartItemRequestDto.getReservationItemId());
			CartReservationInfo cartReservationInfo = CartReservationInfo.builder(postReservationCartItemRequestDto)
				.build();

			cartReservationInfoRepository.save(cartReservationInfo);
			ReservationItemOption reservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				postReservationCartItemRequestDto.getReservationItemOptionId());
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
	public ResponseDto<GetReservationCartItemDto.Response> find(User user, UUID reservationCartItemId) throws Exception {
		try {
			ReservationCart reservationCart = reservationCartService.readReservationCart(user, CartType.RESERVATION);
			ReservationCartItem findReservationCartItem = reservationCartItemRepository.findReservationCartItemById(reservationCartItemId);
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
	public ResponseDto<PatchReservationCartItem.Response> update(User user, UUID reservationCartItemId,
		PatchReservationCartItem.Request patchReservationCartItemRequestDto) throws Exception {
		try {
			ReservationCartItem findReservationCartItem = reservationCartItemRepository.findReservationCartItemById(
				reservationCartItemId);
			ReservationItem findReservationItem = reservationItemRepository.findReservationItemByReservationItemId(
				patchReservationCartItemRequestDto.getReservationItemId());
			ReservationItemOption findReservationItemOption = reservationItemOptionRepository.findReservationItemOptionByOptionId(
				patchReservationCartItemRequestDto.getReservationItemOptionId());
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
	public ResponseDto<DeleteReservationCartItem.Response> delete(User user, UUID reservationCartItemId) throws Exception{
		try {
			ReservationCartItem findReservationCartItem = reservationCartItemRepository.findReservationCartItemById(reservationCartItemId);
			findReservationCartItem.delete();
			reservationCartItemRepository.save(findReservationCartItem);

			return ResponseDto.<DeleteReservationCartItem.Response>builder()
				.responseCode(ResponseCode.CART_RESERVATION_ITEM_READ)
				.data(DeleteReservationCartItem.Response.builder(findReservationCartItem).build())
				.build();
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
