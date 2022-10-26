package com.tmax.cm.superstore.mypage.util;

import java.util.Comparator;

import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.user.entities.DeliveryAddress;

public class ReviewComparotor implements Comparator<Review> {
	@Override
	public int compare(Review o1, Review o2) {
		if(o1.getCreatedAt().isBefore(o2.getCreatedAt())) return -1;
		return 0;
	}
}
