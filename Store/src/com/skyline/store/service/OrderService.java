package com.skyline.store.service;

import com.skyline.store.domain.Order;
import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.User;

public interface OrderService {
	
	void saveOrder(Order order) throws Exception;

	PageModel findMyOrderWithPage(User user, int curNum) throws Exception;

	Order findOrderByOid(String oid) throws Exception;


}
