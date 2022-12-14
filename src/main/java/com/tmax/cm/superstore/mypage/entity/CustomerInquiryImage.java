// package com.tmax.cm.superstore.mypage.entity;
//
// import java.util.UUID;
//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
//
// import org.hibernate.annotations.GenericGenerator;
//
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Entity
// @Getter
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// public class CustomerInquiryImage {
// 	@Id @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
// 	@GeneratedValue(generator = "UUID")
// 	@Column(name = "CUSTOMER_INQUIRY_IMAGE_ID", columnDefinition = "BINARY(16)")
// 	private UUID id;
//
// 	private String url;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "CUSTOMER_INQUIRY_ID")
// 	private CustomerInquiry customerInquiry;
// }
