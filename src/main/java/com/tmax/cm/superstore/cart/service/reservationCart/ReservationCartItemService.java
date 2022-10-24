package com.tmax.cm.superstore.cart.service.reservationCart;

import com.tmax.cm.superstore.cart.dto.reservationCart.PostReservationCartItemDto;
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
}
