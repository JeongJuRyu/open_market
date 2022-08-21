package com.tmax.cm.superstore.mypage.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Review {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "REVIEW_ID")
	private UUID id;

	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ITEM_ID")
	private OrderItem orderItem;*/

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;*/

	@OneToMany(mappedBy = "review")
	private List<ReviewImage> reviewImages = new ArrayList<>();

	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REVIEW_REPLY_ID")
	private ReviewReply reviewReply;
	 */
	/*public static ReviewBuilder builder(String title, String content
								,User user, List<ReviewImage> reviewImages){
		return ReviewBuilder()
			.title(title)
			.content(content)
			.user(user)
			.reviewImages(reviewImages);
	}*/

}
