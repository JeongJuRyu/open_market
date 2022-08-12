package com.tmax.cm.superstore.mypage.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.user.entities.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(builderMethodName = "ReviewImageBuilder")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage {
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	/*@OneToMany(mappedBy = "order")
	List<OrderItem> orderItems;*/

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "review")
	private List<ReviewImage> reviewImages = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REVIEW_REPLY_ID")
	private ReviewReply reviewReply;

	public static ReviewImageBuilder builder(/*OrderItem orderItem, */String title,
									String content, User user, List<ReviewImage> reviewImages){
		return ReviewImageBuilder()
			//.orderItem(orderItem)
			.title(title)
			.content(content)
			.user(user)
			.reviewImages(reviewImages);
	}
}
