package com.tmax.cm.superstore.mypage.util;

import java.util.Comparator;

import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.user.entities.DeliveryAddress;

public class ReviewComparotor implements Comparator<Review> {
	@Override
	public int compare(Review o1, Review o2) {
		if(o2.getCreatedAt().isBefore(o1.getCreatedAt())) return -1;
		return 0;
	}
}
