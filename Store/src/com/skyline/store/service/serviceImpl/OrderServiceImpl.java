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
		//���涩���Ͷ��������еĶ�����(ͬʱ�ɹ�����ʧ��)
		Connection conn = null;
		try {
			//��ȡ����
			conn = JDBCUtils.getConnection();
			//��������
			conn.setAutoCommit(false);
			//���涩��
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
		//����pageModel����Я����ҳ����
		PageModel pm = new PageModel(curNum, totalRecord, 3);
		//��������
		List list = orderDao.findMyOrderWithPage(user, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		//����url
		pm.setUrl("OrderServlet?method=findMyOrderWithPage");
		return pm;
	}

	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
	}

}
