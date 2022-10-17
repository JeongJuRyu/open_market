package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.common.entity.BaseTimeEntity;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.PostReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewReplyRequestDto;
import com.tmax.cm.superstore.mypage.dto.UpdateReviewRequestDto;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.user.entities.User;

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

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private Float starRating;

	@Column(nullable = false)
	private Long isUseful;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private PickupOrderItem orderItem;

	@OneToOne(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private ReviewReply reviewReply;

	public void updateReview(UpdateReviewRequestDto dto) {
		this.title = dto.getTitle();
		this.content = dto.getContent();
	}

	public void setReviewReply(PostReviewReplyRequestDto dto) {
		this.reviewReply = ReviewReply.ReviewReplyBuilder()
				.review(this)
				.content(dto.getContent())
				.build();
	}

	public void updateReviewReply(UpdateReviewReplyRequestDto dto) {
		this.reviewReply = ReviewReply.ReviewReplyBuilder()
				.review(this)
				.content(dto.getContent())
				.build();
	}

	public void deleteReviewReply() {
		this.reviewReply = null;
	}
}
