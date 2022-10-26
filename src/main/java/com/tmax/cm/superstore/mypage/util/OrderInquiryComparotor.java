package com.tmax.cm.superstore.mypage.util;

import java.util.Comparator;

import com.tmax.cm.superstore.mypage.entity.OrderInquiry;

public class OrderInquiryComparotor implements Comparator<OrderInquiry> {
	@Override
	public int compare(OrderInquiry o1, OrderInquiry o2) {
		if(o1.getCreatedAt().isBefore(o2.getCreatedAt())) return -1;
		return 0;
	}
}
