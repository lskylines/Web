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
	//提交后保存订单
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//确定用户是否登录状态
		 User user = (User)request.getSession().getAttribute("loginuser");
		if(user==null) {
			request.setAttribute("msg",  "请登录后再下单");
			return "/jsp/info2.jsp";
		}
		//获取购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		 //创建订单对象，为订单对象赋值
		 Order order = new Order();
		 order.setOrdertime(new Date());
		 order.setState(1);
		 order.setOid(UUIDUtils.getCode());
		order.setTotal(cart.getTotal());
		order.setUser(user);
		//遍历购物项的同时，创建订单项，为订单项赋值
		for (CartItem item :  cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		//调用业务层，保存订单
		OrderService orderService = new OrderServiceImpl();
		orderService.saveOrder(order);
		//清空购物车 
		cart.clearCart();
		//将订单放入request中
		request.setAttribute("order", order);
		//转发/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}
	
	
	public String findMyOrderWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("findMyOrderWithPage poiint");
		//获取用户信息
		User user = (User)request.getSession().getAttribute("loginuser");
		//获取当前页
		int curNum = Integer.parseInt(request.getParameter("num"));
		//调用业务层功能：查询用户订单信息，返回pageModel
		OrderService orderService = new OrderServiceImpl();
		//SELECT * FROM orders WHERE uid=? limit ?,?
		PageModel pm = orderService.findMyOrderWithPage(user, curNum);
		//将pageModel放入request
		request.setAttribute("page", pm);
		//转发到jsp/order_list.jsp
		return "/jsp/order_list.jsp";
	}
	
	public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取订单oid
		String oid = request.getParameter("oid");
		//调用业务层功能：根据订单编号查询订单信息
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		//将订单信息放入request中
		request.setAttribute("order",  order);
		//转发到/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}

}
