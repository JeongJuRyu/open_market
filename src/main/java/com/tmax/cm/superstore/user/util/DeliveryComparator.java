package com.tmax.cm.superstore.user.util;

import java.util.Comparator;

import com.tmax.cm.superstore.user.entities.DeliveryAddress;

public class DeliveryComparator implements Comparator<DeliveryAddress> {
	@Override
	public int compare(DeliveryAddress o1, DeliveryAddress o2) {
		if(o1.getIsDefaultAddress()) return -1;
		else if(o2.getIsDefaultAddress()) return 1;
		else{
			if(o1.getCreatedAt().isBefore(o2.getCreatedAt())) return -1;
			return 1;
		}
	}
}
