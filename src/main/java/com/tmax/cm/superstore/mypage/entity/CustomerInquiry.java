// package com.tmax.cm.superstore.mypage.entity;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;
//
// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
//
// import org.hibernate.annotations.GenericGenerator;
//
// import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
// import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryRequestDto;
// import com.tmax.cm.superstore.order.entity.OrderItem;
// import com.tmax.cm.superstore.seller.entity.Seller;
// import com.tmax.cm.superstore.user.entities.User;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Getter
// @Entity
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// public class CustomerInquiry extends BaseTimeEntity {
// 	@Id @GeneratedValue(generator = "UUID")
// 	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
// 	@Column(name = "CUSTOMER_INQUIRY_ID", columnDefinition = "BINARY(16)")
// 	private UUID id;
//
// 	@Column(nullable = false)
// 	private String title;
//
// 	@Column(nullable = false)
// 	private String content;
//
// 	@Column(nullable = false)
// 	private Boolean isProcessed;
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "USER_ID")
// 	private User user;
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "SELLER_ID")
// 	private Seller seller;
//
// 	@OneToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "ORDER_ITEM_ID")
// 	private OrderItem orderItem;
//
// 	@OneToOne(mappedBy = "customerInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
// 	private CustomerInquiryReply customerInquiryReply;
//
// 	@OneToMany(mappedBy = "customerInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
// 	@Builder.Default
// 	private List<CustomerInquiryImage> customerInquiryImages = new ArrayList<>();
//
//
// 	public void updateInquiry(UpdateCustomerInquiryRequestDto dto){
//
// 	}
// 	public void updateReply(CustomerInquiryReply customerInquiryReply){
// 		this.customerInquiryReply = customerInquiryReply;
// 	}
//
// 	public void deleteReply(UUID customerInquiryReplyId){
// 		this.customerInquiryReply = null;
// 		this.isProcessed = false;
// 	}
// }
