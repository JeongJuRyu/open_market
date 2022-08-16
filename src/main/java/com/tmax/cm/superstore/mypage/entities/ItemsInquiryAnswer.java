package com.tmax.cm.superstore.mypage.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "itemsInquiryAnswerBuilder")
@Entity
public class ItemsInquiryAnswer {
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ITEMS_INQUIRY_ANSWER_ID")
	private UUID id;

	@Column(nullable = false)
	private String content;


}
