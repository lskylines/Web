package com.skyline.store.dao.daoImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.skyline.store.dao.OrderDao;
import com.skyline.store.domain.Order;
import com.skyline.store.domain.OrderItem;
import com.skyline.store.domain.Product;
import com.skyline.store.domain.User;
import com.skyline.store.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	public void saveOrder(Connection conn, Order order) throws Exception {
		String sql  = "INSERT INTO orders values(?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {order.getOid(),order.getOrdertime(),order.getTotal(), order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
		qr.update(conn, sql, params);
	}

	public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
		String sql  = "INSERT INTO orderitem values(?,?,?,?,?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {item.getItemid(), item.getQuantity(), item.getTotal(), item.getProduct().getPid(), item.getOrder().getOid()};
		qr.update(conn, sql, params);
	}
	
	
	//��ȡĳһָ���û�ȫ������
	public int getTotalRecord(User user) throws Exception {
		String sql = "SELECT COUNT(*) FROM orders WHERE uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)qr.query(sql, new  ScalarHandler(), user.getUid());
		return num.intValue();
	}

	//��ѯ�û�����pageSize����������
	public List findMyOrderWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql = "SELECT  * FROM orders WHERE uid=? limit ?, ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
		for (Order order : list) {
			//��ȡÿ�ʶ���oid
			String oid = order.getOid();
			sql = "SELECT * FROM orderItem o, product p WHERE o.pid = p.pid AND oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), oid);
			//����listo2
			for(Map<String,Object> map:list02) {
				OrderItem orderItem = new OrderItem();
				Product product  = new Product();
				//����ʱ��ת����
				DateConverter dt = new DateConverter();
				//����ʱ���ʽ
				dt.setPattern("yyyy-MM-dd");
				//ע��ת����
				ConvertUtils.register(dt, java.util.Date.class);
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map );
				orderItem.setProduct(product);
				//��ÿ�������Ͷ����µļ��Ϸ���g������ϵ
				order.getList().add(orderItem);
			}
		}
		return list;
	}

	public Order findOrderByOid(String oid) throws Exception {
		String sql  ="SELECT * FROM orders WHERE oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		sql = "SELECT * FROM orderitem o, product p WHERE o.pid=p.pid AND oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(), oid);
		//����listo2
		for(Map<String,Object> map:list02) {
			OrderItem orderItem = new OrderItem();
			Product product  = new Product();
			//����ʱ��ת����
			DateConverter dt = new DateConverter();
			//����ʱ���ʽ
			dt.setPattern("yyyy-MM-dd");
			//ע��ת����
			ConvertUtils.register(dt, java.util.Date.class);
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map );
			orderItem.setProduct(product);
			//��ÿ�������Ͷ����µļ��Ϸ���g������ϵ
			order.getList().add(orderItem);
		}
		return order;
	}

}
