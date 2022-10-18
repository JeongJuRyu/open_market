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
			CreateSellerDto.SellerInfo sellerInfo = createSellerInfo("rala", "rala123", "랄라샌드위치", "rala@naver.com",
				"0507-1367-2348", "탄천상로151번길 20");
			CreateSellerDto.BizInfo bizInfo = createBizInfo("사업자등록번호", "상호명", "대표명", "사업장소재지", "통신판매업번호");
			CreateSellerDto.SellerDeliveryInfo sellerDeliveryInfo = createSellerDeliveryInfo("출고지 주소", "출고지 상세 주소",
				"반품지 주소", "반품지 상세주소");
			CreateSellerDto.Request createSellerRequestDto = new CreateSellerDto.Request(sellerInfo, bizInfo,
				sellerDeliveryInfo);
			Seller seller1 = createSeller(createSellerRequestDto);

			// 예약상품 생성
			ReservationItem reservationItem1 = createReservationItem("치킨텐더 샌드위치", "5500", "치킨텐더가 들어간 샌드위치 입니다",
				"토마토, 양상추 등의 야채가 들어가니 주의하세요", 1, "30",
				LocalTime.of(8, 00), LocalTime.of(20, 00), seller1);

			// 예약상품 이미지 등록
			createReservationItemImage(reservationItem1, "치킨센더 샌드위치");

			// 예약상품 옵션 생성
			createReservationItemOption(reservationItem1, "기본", "0", "기본 구성입니다.");
			createReservationItemOption(reservationItem1, "계란추가", "500", "계란 후라이 형태로 추가됩니다.");
			createReservationItemOption(reservationItem1, "아이스 아메리카노 추가", "1500", "커피와 세트메뉴로 제공됩니다.");

			// 예약상품 생성
			ReservationItem reservationItem2 = createReservationItem("푸실리 샐러드", "5500", "푸실리드가 들어간 샐러드 입니다",
				"토마토, 양상추 등의 야채가 들어가니 주의하세요", 2, "60",
				LocalTime.of(8, 00), LocalTime.of(20, 00), seller1);

			// 예약상품 이미지 등록
			createReservationItemImage(reservationItem2, "푸실리 샐러드");

			// 예약상품 옵션 생성
			createReservationItemOption(reservationItem2, "기본", "0", "기본 구성입니다.");
			createReservationItemOption(reservationItem2, "계란추가", "500", "삶은 계란 슬라이스 형태로 추가됩니다.");
			createReservationItemOption(reservationItem2, "아이스 아메리카노 추가", "1500", "커피와 세트메뉴로 제공됩니다.");
		}

		public void dbInit2() {
			CreateSellerDto.SellerInfo sellerInfo = createSellerInfo("byulme", "byulme123", "별미", "byulme@naver.com",
				"031-782-9588","탄천상로151번길 20");
			CreateSellerDto.BizInfo bizInfo = createBizInfo("사업자등록번호", "상호명", "대표명", "사업장소재지", "통신판매업번호");
			CreateSellerDto.SellerDeliveryInfo sellerDeliveryInfo = createSellerDeliveryInfo("출고지 주소", "출고지 상세 주소",
				"반품지 주소", "반품지 상세주소");
			CreateSellerDto.Request createSellerRequestDto = new CreateSellerDto.Request(sellerInfo, bizInfo,
				sellerDeliveryInfo);
			Seller seller2 = createSeller(createSellerRequestDto);

			// 예약상품 생성
			ReservationItem reservationItem1 = createReservationItem("직화제육 덮밥", "7500", "직화제육이 들어간 덮밥 입니다",
				"밥 양이 많으니 적게 드실 분은 미리 말씀해주세", 3, "30",
				LocalTime.of(11, 00), LocalTime.of(20, 00), seller2);

			// 예약상품 이미지 등록
			createReservationItemImage(reservationItem1, "직화제육 덮밥");

			// 예약상품 옵션 생성
			createReservationItemOption(reservationItem1, "기본", "0", "기본 구성입니다.");
			createReservationItemOption(reservationItem1, "계란 후라이 추가", "500", "계란 후라이가 덮밥에 추가됩니다.");
			createReservationItemOption(reservationItem1, "직화제육 추가", "2000", "곱배기 메뉴입니다.");

			// 예약상품 생성
			ReservationItem reservationItem2 = createReservationItem("회오리 오무라이스", "7000", "회오리 형태 오무라이스 입니다",
				"볶음밥에 새우가 들어가니 주의하세요.",
				2, "30",
				LocalTime.of(11, 00), LocalTime.of(20, 00), seller2);

			// 예약상품 이미지 등록
			createReservationItemImage(reservationItem2, "회오리 오무라이스");

			// 예약상품 옵션 생성
			createReservationItemOption(reservationItem2, "기본", "0", "기본 구성입니다.");
			createReservationItemOption(reservationItem2, "계란 추가", "1000", "계란 지단이 한 장 추가됩니다.");
			createReservationItemOption(reservationItem2, "곱배기", "1500", "곱배기 메뉴입니다.");
		}

		private CreateSellerDto.SellerInfo createSellerInfo(String loginId, String password, String sellerName,
			String sellerEmail, String sellerPhoneNum, String address) {
			return CreateSellerDto.SellerInfo.SellerInfoBuilder()
				.loginId(loginId)
				.password(password)
				.sellerName(sellerName)
				.sellerEmail(sellerEmail)
				.sellerPhoneNum(sellerPhoneNum)
				.address(address).build();
		}

		private CreateSellerDto.BizInfo createBizInfo(String bizNum, String bizName, String bizOwner, String bizAddress,
			String reportNumber) {
			return CreateSellerDto.BizInfo.BizInfoBuilder()
				.bizNum(bizNum)
				.bizName(bizName)
				.bizOwner(bizOwner)
				.bizAddress(bizAddress)
				.reportNumber(reportNumber).build();
		}

		private CreateSellerDto.SellerDeliveryInfo createSellerDeliveryInfo(String shipmentAddress,
			String shipmentAddressDetail, String returnAddress, String returnAddressDetail) {
			return CreateSellerDto.SellerDeliveryInfo.SellerDeliveryInfoBuilder()
				.shipmentAddress(shipmentAddress)
				.shipmentAddressDetail(shipmentAddressDetail)
				.returnAddress(returnAddress)
				.returnAddressDetail(returnAddressDetail).build();
		}

		private Seller createSeller(CreateSellerDto.Request createSellerRequestDto) {
			Seller seller = Seller.builder(createSellerRequestDto).build();
			sellerRepository.save(seller);
			Business business = Business.builder(seller, createSellerRequestDto).build();
			businessRepository.save(business);
			SellerDelivery sellerDelivery = SellerDelivery.builder(seller, createSellerRequestDto).isRepresent(true)
				.build();
			sellerDeliveryRepository.save(sellerDelivery);
			return seller;
		}

		public ReservationItem createReservationItem(String reservationItemName, String reservationItemPrice,
			String reservationItemDescription, String reservationItemNotice, Integer allowReservationNumberPerInterval,
			String reservationInterval, LocalTime start, LocalTime end, Seller seller) {
			ReservationItem reservationItem = ReservationItem.builder(
					new CreateReservationItemDto.Request(reservationItemName, reservationItemPrice,
						reservationItemDescription,
						reservationItemNotice, allowReservationNumberPerInterval, reservationInterval, start, end), seller)
				.build();
			reservationItemRepository.save(reservationItem);
			return reservationItem;
		}

		private void createReservationItemImage(ReservationItem reservationItem, String imageName) {
			ReservationItemImage reservationItemImage = ReservationItemImage.builder(
				new CreateReservationItemImageDto.Request(
					UUID.randomUUID(), imageName), reservationItem).build();
			reservationItemImageRepository.save(reservationItemImage);
		}

		private void createReservationItemOption(ReservationItem reservationItem, String optionName, String optionPrice,
			String optionDescription) {
			ReservationItemOption reservationItemOption = ReservationItemOption.builder(
					new CreateReservationItemOptionDto.Request(optionName, optionPrice, optionDescription), reservationItem)
				.build();
			reservationItemOptionRepository.save(reservationItemOption);
		}
	}
}
