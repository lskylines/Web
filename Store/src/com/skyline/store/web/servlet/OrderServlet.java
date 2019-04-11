package com.skyline.store.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyline.store.domain.Cart;
import com.skyline.store.domain.CartItem;
import com.skyline.store.domain.Order;
import com.skyline.store.domain.OrderItem;
import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.User;
import com.skyline.store.service.OrderService;
import com.skyline.store.service.serviceImpl.OrderServiceImpl;
import com.skyline.store.utils.UUIDUtils;
import com.skyline.store.web.base.BaseServlet;

public class OrderServlet extends BaseServlet{	
	//�ύ�󱣴涩��
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ȷ���û��Ƿ��¼״̬
		 User user = (User)request.getSession().getAttribute("loginuser");
		if(user==null) {
			request.setAttribute("msg",  "���¼�����µ�");
			return "/jsp/info2.jsp";
		}
		//��ȡ���ﳵ
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		 //������������Ϊ��������ֵ
		 Order order = new Order();
		 order.setOrdertime(new Date());
		 order.setState(1);
		 order.setOid(UUIDUtils.getCode());
		order.setTotal(cart.getTotal());
		order.setUser(user);
		//�����������ͬʱ�����������Ϊ�����ֵ
		for (CartItem item :  cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		//����ҵ��㣬���涩��
		OrderService orderService = new OrderServiceImpl();
		orderService.saveOrder(order);
		//��չ��ﳵ 
		cart.clearCart();
		//����������request��
		request.setAttribute("order", order);
		//ת��/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}
	
	
	public String findMyOrderWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("findMyOrderWithPage poiint");
		//��ȡ�û���Ϣ
		User user = (User)request.getSession().getAttribute("loginuser");
		//��ȡ��ǰҳ
		int curNum = Integer.parseInt(request.getParameter("num"));
		//����ҵ��㹦�ܣ���ѯ�û�������Ϣ������pageModel
		OrderService orderService = new OrderServiceImpl();
		//SELECT * FROM orders WHERE uid=? limit ?,?
		PageModel pm = orderService.findMyOrderWithPage(user, curNum);
		//��pageModel����request
		request.setAttribute("page", pm);
		//ת����jsp/order_list.jsp
		return "/jsp/order_list.jsp";
	}
	
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��ȡ����oid
		String oid = request.getParameter("oid");
		//����ҵ��㹦�ܣ����ݶ�����Ų�ѯ������Ϣ
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		//��������Ϣ����request��
		request.setAttribute("order",  order);
		//ת����/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}

}
