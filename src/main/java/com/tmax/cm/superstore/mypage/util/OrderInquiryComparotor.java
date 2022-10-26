package com.tmax.cm.superstore.mypage.util;

import java.util.Comparator;

import com.tmax.cm.superstore.mypage.entity.OrderInquiry;

public class OrderInquiryComparotor implements Comparator<OrderInquiry> {
	@Override
	public int compare(OrderInquiry o1, OrderInquiry o2) {
		if(o2.getCreatedAt().isBefore(o1.getCreatedAt())) return -1;
		return 0;
	}
}
