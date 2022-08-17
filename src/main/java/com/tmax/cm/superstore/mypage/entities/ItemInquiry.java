package com.tmax.cm.superstore.mypage.entities;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ItemInquiry {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Column (nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@OneToMany(mappedBy = "itemsInquiry")
	private List<ItemInquiryImage> itemsInquiryImages;

	@OneToMany(mappedBy = "itemsInquiryAnswer")
	private List<ItemInquiryAnswer> itemInquiryAnswers;
}
