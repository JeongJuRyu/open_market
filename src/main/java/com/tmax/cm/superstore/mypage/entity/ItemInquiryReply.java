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
// import javax.persistence.OneToOne;
//
// import org.hibernate.annotations.GenericGenerator;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @Entity
// public class ItemInquiryReply {
// 	@Id @GeneratedValue(generator = "UUID")
// 	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
// 	@Column(name = "ITEM_INQUIRY_ANSWER_ID", columnDefinition = "BINARY(16)")
// 	private UUID id;
//
// 	@Column(nullable = false)
// 	private String content;
//
// 	@OneToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "ITEM_INQUIRY_ID")
// 	private ItemInquiry itemInquiry;
//
// }
