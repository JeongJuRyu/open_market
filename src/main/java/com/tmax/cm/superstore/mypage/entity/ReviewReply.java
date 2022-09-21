package com.tmax.cm.superstore.mypage.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.tmax.cm.superstore.mypage.dto.UpdateReviewReplyRequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(builderMethodName = "ReviewReplyBuilder")
public class ReviewReply {
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "REVIEW_REPLY_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String content;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REIVEW_ID")
	private Review review;

	public void updateReviewReply(UpdateReviewReplyRequestDto dto) {
		this.content = dto.getContent();
	}
}
