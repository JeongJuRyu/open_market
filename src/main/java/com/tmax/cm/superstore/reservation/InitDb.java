package com.tmax.cm.superstore.reservation;

import com.tmax.cm.superstore.reservation.dto.CreateReservationItemDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemImageDto;
import com.tmax.cm.superstore.reservation.dto.CreateReservationItemOptionDto;
import com.tmax.cm.superstore.reservation.entity.ReservationItem;
import com.tmax.cm.superstore.reservation.entity.ReservationItemImage;
import com.tmax.cm.superstore.reservation.entity.ReservationItemOption;
import com.tmax.cm.superstore.reservation.repository.ReservationItemImageRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemOptionRepository;
import com.tmax.cm.superstore.reservation.repository.ReservationItemRepository;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.entity.Business;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.entity.SellerDelivery;
import com.tmax.cm.superstore.seller.repository.BusinessRepository;
import com.tmax.cm.superstore.seller.repository.SellerDeliveryRepository;
import com.tmax.cm.superstore.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitDb {

	private final InitService initService;

	@PostConstruct
	public void init() {
		initService.dbInit1();
		initService.dbInit2();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		private final SellerRepository sellerRepository;
		private final BusinessRepository businessRepository;
		private final SellerDeliveryRepository sellerDeliveryRepository;
		private final ReservationItemRepository reservationItemRepository;
		private final ReservationItemOptionRepository reservationItemOptionRepository;
		private final ReservationItemImageRepository reservationItemImageRepository;

		public void dbInit1() {
			CreateSellerDto.SellerInfo sellerInfo = CreateSellerDto.SellerInfo.SellerInfoBuilder()
				.loginId("rala")
				.password("rala123")
				.sellerName("랄라샌드위치")
				.sellerEmail("rala@naver.com")
				.sellerPhoneNum("0507-1367-2348").build();

			CreateSellerDto.BizInfo bizInfo = CreateSellerDto.BizInfo.BizInfoBuilder()
				.bizNum("사업자등록번호")
				.bizName("상호명")
				.bizOwner("대표자명")
				.bizAddress("사업장소재지")
				.reportNumber("통신판매업번호").build();

			CreateSellerDto.SellerDeliveryInfo sellerDeliveryInfo = CreateSellerDto.SellerDeliveryInfo.SellerDeliveryInfoBuilder()
				.shipmentAddress("출고지 주소")
				.shipmentAddressDetail("출고지 상세주소")
				.returnAddress("반품지 주소")
				.returnAddressDetail("반품지 상세주소").build();

			CreateSellerDto.Request createSellerRequestDto = new CreateSellerDto.Request(sellerInfo, bizInfo, sellerDeliveryInfo);

			Seller seller1 = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(seller1);
			Business business1 = Business.builder(seller1, createSellerRequestDto).build();
			businessRepository.save(business1);
			SellerDelivery sellerDelivery1 = SellerDelivery.builder(seller1, createSellerRequestDto).build();
			sellerDeliveryRepository.save(sellerDelivery1);

			// 예약상품 생성
			ReservationItem reservationItem1 = ReservationItem.builder(
				new CreateReservationItemDto.Request("치킨텐더 샌드위치", "5500", "치킨텐더가 들어간 샌드위치 입니다",
					"토마토, 양상추 등의 야채가 들어가니 주의하세요", 1, "30",
					LocalTime.of(8, 00), LocalTime.of(20, 00)), seller1).build();
			reservationItemRepository.save(reservationItem1);
			// 예약상품 이미지 등록
			ReservationItemImage reservationItem1Image = ReservationItemImage.builder(
				new CreateReservationItemImageDto.Request(
					UUID.randomUUID(), "치킨센더 샌드위치"), reservationItem1).build();
			reservationItemImageRepository.save(reservationItem1Image);

			// 예약상품 옵션 생성
			ReservationItemOption reservationItem1Option1 = ReservationItemOption.builder(
					new CreateReservationItemOptionDto.Request("계란추가", "500", "계란 후라이 형태로 추가됩니다."), reservationItem1)
				.build();
			reservationItemOptionRepository.save(reservationItem1Option1);
			ReservationItemOption reservationItem1Option2 = ReservationItemOption.builder(
				new CreateReservationItemOptionDto.Request("아이스 아메리카노 추가", "1500", "커피와 세트메뉴로 제공됩니다."),
				reservationItem1).build();
			reservationItemOptionRepository.save(reservationItem1Option2);

			// 예약상품 생성
			ReservationItem reservationItem2 = ReservationItem.builder(
				new CreateReservationItemDto.Request("푸실리 샐러드", "5500", "푸실리드가 들어간 샐러드 입니다",
					"토마토, 양상추 등의 야채가 들어가니 주의하세요", 2, "60",
					LocalTime.of(8, 00), LocalTime.of(20, 00)), seller1).build();
			reservationItemRepository.save(reservationItem2);
			// 예약상품 이미지 등록
			ReservationItemImage reservationItem2Image = ReservationItemImage.builder(
				new CreateReservationItemImageDto.Request(
					UUID.randomUUID(), "푸실리 샐러드"), reservationItem2).build();
			reservationItemImageRepository.save(reservationItem2Image);

			// 예약상품 옵션 생성
			ReservationItemOption reservationItem2Option1 = ReservationItemOption.builder(
					new CreateReservationItemOptionDto.Request("계란추가", "500", "삶은 계란 슬라이스 형태로 추가됩니다."), reservationItem2)
				.build();
			reservationItemOptionRepository.save(reservationItem2Option1);
			ReservationItemOption reservationItem2Option2 = ReservationItemOption.builder(
				new CreateReservationItemOptionDto.Request("아이스 아메리카노 추가", "1500", "커피와 세트메뉴로 제공됩니다."),
				reservationItem2).build();
			reservationItemOptionRepository.save(reservationItem2Option2);
		}

		public void dbInit2() {
			CreateSellerDto.SellerInfo sellerInfo = CreateSellerDto.SellerInfo.SellerInfoBuilder()
				.loginId("byulme")
				.password("byulme123")
				.sellerName("별미")
				.sellerEmail("byulme@naver.com")
				.sellerPhoneNum("031-782-9588").build();

			CreateSellerDto.BizInfo bizInfo = CreateSellerDto.BizInfo.BizInfoBuilder()
				.bizNum("사업자등록번호")
				.bizName("상호명")
				.bizOwner("대표자명")
				.bizAddress("사업장소재지")
				.reportNumber("통신판매업번호").build();

			CreateSellerDto.SellerDeliveryInfo sellerDeliveryInfo = CreateSellerDto.SellerDeliveryInfo.SellerDeliveryInfoBuilder()
				.shipmentAddress("출고지 주소")
				.shipmentAddressDetail("출고지 상세주소")
				.returnAddress("반품지 주소")
				.returnAddressDetail("반품지 상세주소").build();

			CreateSellerDto.Request createSellerRequestDto = new CreateSellerDto.Request(sellerInfo, bizInfo, sellerDeliveryInfo);

			Seller seller2 = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(seller2);
			Business business2 = Business.builder(seller2, createSellerRequestDto).build();
			businessRepository.save(business2);
			SellerDelivery sellerDelivery2 = SellerDelivery.builder(seller2, createSellerRequestDto).build();
			sellerDeliveryRepository.save(sellerDelivery2);

			// 예약상품 생성
			ReservationItem reservationItem1 = ReservationItem.builder(
				new CreateReservationItemDto.Request("직화제육 덮밥", "7500", "직화제육이 들어간 덮밥 입니다",
					"밥 양이 많으니 적게 드실 분은 미리 말씀해주세", 3, "30",
					LocalTime.of(11, 00), LocalTime.of(20, 00)), seller2).build();
			reservationItemRepository.save(reservationItem1);
			// 예약상품 이미지 등록
			ReservationItemImage reservationItem1Image = ReservationItemImage.builder(
				new CreateReservationItemImageDto.Request(
					UUID.randomUUID(), "직화제육 덮밥"), reservationItem1).build();
			reservationItemImageRepository.save(reservationItem1Image);

			// 예약상품 옵션 생성
			ReservationItemOption reservationItem1Option1 = ReservationItemOption.builder(
					new CreateReservationItemOptionDto.Request("계란 후라이 추가", "500", "계란 후라이가 덮밥에 추가됩니다."), reservationItem1)
				.build();
			reservationItemOptionRepository.save(reservationItem1Option1);
			ReservationItemOption reservationItem1Option2 = ReservationItemOption.builder(
				new CreateReservationItemOptionDto.Request("직화제육 추가", "2000", "곱배기 메뉴입니다."), reservationItem1).build();
			reservationItemOptionRepository.save(reservationItem1Option2);

			// 예약상품 생성
			ReservationItem reservationItem2 = ReservationItem.builder(
				new CreateReservationItemDto.Request("회오리 오무라이스", "7000", "회오리 형태 오무라이스 입니다", "볶음밥에 새우가 들어가니 주의하세요.",
					2, "30",
					LocalTime.of(11, 00), LocalTime.of(20, 00)), seller2).build();
			reservationItemRepository.save(reservationItem2);
			// 예약상품 이미지 등록
			ReservationItemImage reservationItem2Image = ReservationItemImage.builder(
				new CreateReservationItemImageDto.Request(
					UUID.randomUUID(), "회오리 오무라이스"), reservationItem2).build();
			reservationItemImageRepository.save(reservationItem2Image);

			// 예약상품 옵션 생성
			ReservationItemOption reservationItem2Option1 = ReservationItemOption.builder(
					new CreateReservationItemOptionDto.Request("계란 추가", "1000", "계란 지단이 한 장 추가됩니다."), reservationItem2)
				.build();
			reservationItemOptionRepository.save(reservationItem2Option1);
			ReservationItemOption reservationItem2Option2 = ReservationItemOption.builder(
				new CreateReservationItemOptionDto.Request("곱배기", "1500", "곱배기 메뉴입니다."), reservationItem2).build();
			reservationItemOptionRepository.save(reservationItem2Option2);
		}
	}
}
