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
// import com.tmax.cm.superstore.user.entities.User;
//
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// @Entity
// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor
// @Builder
// public class ItemInquiry extends BaseTimeEntity {
// 	@Id
// 	@GeneratedValue(generator = "UUID")
// 	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
// 	@Column(name = "ITEM_INQUIRY_ID", columnDefinition = "BINARY(16)")
// 	private UUID id;
//
// 	@Column (nullable = false)
// 	private String title;
//
// 	@Column(nullable = false)
// 	private String content;
//
// 	@OneToMany(mappedBy = "itemInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
// 	@Builder.Default
// 	private List<ItemInquiryImage> itemInquiryImages = new ArrayList<>();
//
// 	@OneToOne(mappedBy = "itemInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
// 	private ItemInquiryReply itemInquiryReply;
//
//
// 	@ManyToOne(fetch = FetchType.LAZY)
// 	@JoinColumn(name = "USER_ID")
// 	private User user;
// 	// @ManyToOne(fetch = FetchType.LAZY)
// 	// @JoinColumn(name = "ITEM_ID")
// 	// private Item item;
// 	public void postReply(ItemInquiryReply itemInquiryReply){
// 		this.itemInquiryReply = itemInquiryReply;
// 	}
// 	public void removeItemInquiryReply(){
// 		this.itemInquiryReply = null;
// 	}
// }
