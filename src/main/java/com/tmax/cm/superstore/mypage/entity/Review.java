package com.tmax.cm.superstore.mypage.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.PostReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;
import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(builderMethodName = "ReviewBuilder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseTimeEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "REVIEW_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_review_item_id"), name = "item_id", nullable = false)
	private Item item;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	private Float starRating;

	private Long numOfUseful;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<ReviewImage> reviewImages = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "REVIEW_REPLY_ID")
	private ReviewReply reviewReply;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SELLER_ID")
	private Seller seller;

	public void updateReview(UpdateReviewRequestDto dto){
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.getReviewImages().clear();
		dto.getReviewImages().forEach(image->
			this.getReviewImages().add(ReviewImage.ReviewImageBuilder()
				.review(this).url(image.getUrl()).build())
		);
	}
	public void setReviewReply(PostReviewReplyRequestDto dto) {
		this.reviewReply = ReviewReply.ReviewReplyBuilder()
			.review(this)
			.content(dto.getContent())
			.build();
	}
	public void updateReviewReply(UpdateReviewReplyRequestDto dto){
		this.reviewReply = ReviewReply.ReviewReplyBuilder()
			.review(this)
			.content(dto.getContent())
			.build();
	}
	public void deleteReviewReply(){
		this.reviewReply = null;
	}
}
