package com.skyline.store.web.servlet;

import com.skyline.store.domain.Cart;
import com.skyline.store.domain.CartItem;
import com.skyline.store.domain.Product;
import com.skyline.store.service.ProductService;
import com.skyline.store.service.serviceImpl.ProductServiceImpl;
import com.skyline.store.web.base.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet {
	public String addCartItemToCar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//从session中取出购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//session中取不到cart
		if(cart==null) {
			//创建cart购物车对象
			cart = new Cart();
			//cart购物车对象放置到session中
			request.getSession().setAttribute("cart",  cart);
		}
		//如果取到，直接使用
		//获取客户端传过来的pid,num
		int num = Integer.parseInt(request.getParameter("quantity"));
		String pid = request.getParameter("pid");
		//根据传过来的pid去调用业务层去数据库查询商品信息
		ProductService productDao = new ProductServiceImpl();
		Product product = productDao.findByPid(pid);
		//获取待购买的购物向项
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setNum(num);
		cartItem.setSubTotal(cart.getTotal());
		cart.addCartItemToCar(cartItem);
		//重定向到product_info.jsp页面
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;

	}
	
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取客户端的pid
		String pid = request.getParameter("id");
		//从session中取出cart购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//移除购物项
		cart.removeCartItem(pid);
		//重定向到cart.jsp页面
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//session中取出cart购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;
	}
}
