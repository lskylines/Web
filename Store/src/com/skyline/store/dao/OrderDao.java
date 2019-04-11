package com.skyline.store.dao;

import java.sql.Connection;
import java.util.List;

import com.skyline.store.domain.Order;
import com.skyline.store.domain.OrderItem;
import com.skyline.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order) throws Exception;

	void saveOrderItem(Connection conn, OrderItem item) throws Exception;
	
	
	//获取某一用户全部订单
	int getTotalRecord(User user) throws Exception;

	List findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

}
