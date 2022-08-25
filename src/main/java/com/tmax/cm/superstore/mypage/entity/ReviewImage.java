package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
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
	@Column(name = "REVIEW_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	private String url;
	/*@OneToMany(mappedBy = "order")
	List<OrderItem> orderItems;*/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REIVEW_ID")
	private Review review;


	public static ReviewImageBuilder builder(/*OrderItem orderItem, */String title,
									String content, User user, Review review){
		return ReviewImageBuilder()
			.review(review);
	}
}
