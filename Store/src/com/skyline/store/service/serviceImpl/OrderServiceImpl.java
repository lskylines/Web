package com.skyline.store.service.serviceImpl;

import java.sql.Connection;
import java.util.List;

import com.skyline.store.dao.OrderDao;
import com.skyline.store.dao.daoImpl.OrderDaoImpl;
import com.skyline.store.domain.Order;
import com.skyline.store.domain.OrderItem;
import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.User;
import com.skyline.store.service.OrderService;
import com.skyline.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = new OrderDaoImpl();
	public void saveOrder(Order order) throws Exception {
		//保存订单和订单下所有的订单项(同时成功或者失败)
		Connection conn = null;
		try {
			//获取链接
			conn = JDBCUtils.getConnection();
			//开启事物
			conn.setAutoCommit(false);
			//保存订单
			orderDao.saveOrder(conn, order);
			for(OrderItem item:order.getList()) {
				orderDao.saveOrderItem(conn, item);
			}
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
		}
		
	}

	public PageModel findMyOrderWithPage(User user, int curNum) throws Exception {
		
		//select count(*) from order where uid=?
		int totalRecord = orderDao.getTotalRecord(user);
		//创建pageModel对象，携带分页参数
		PageModel pm = new PageModel(curNum, totalRecord, 3);
		//关联集合
		List list = orderDao.findMyOrderWithPage(user, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		//关联url
		pm.setUrl("OrderServlet?method=findMyOrderWithPage");
		return pm;
	}

	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
	}

}
