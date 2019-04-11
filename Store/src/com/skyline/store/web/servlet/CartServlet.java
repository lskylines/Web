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
		//��session��ȡ�����ﳵ
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//session��ȡ����cart
		if(cart==null) {
			//����cart���ﳵ����
			cart = new Cart();
			//cart���ﳵ������õ�session��
			request.getSession().setAttribute("cart",  cart);
		}
		//���ȡ����ֱ��ʹ��
		//��ȡ�ͻ��˴�������pid,num
		int num = Integer.parseInt(request.getParameter("quantity"));
		String pid = request.getParameter("pid");
		//���ݴ�������pidȥ����ҵ���ȥ���ݿ��ѯ��Ʒ��Ϣ
		ProductService productDao = new ProductServiceImpl();
		Product product = productDao.findByPid(pid);
		//��ȡ������Ĺ�������
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setNum(num);
		cartItem.setSubTotal(cart.getTotal());
		cart.addCartItemToCar(cartItem);
		//�ض���product_info.jspҳ��
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;

	}
	
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��ȡ�ͻ��˵�pid
		String pid = request.getParameter("id");
		//��session��ȡ��cart���ﳵ
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//�Ƴ�������
		cart.removeCartItem(pid);
		//�ض���cart.jspҳ��
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//session��ȡ��cart���ﳵ
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clearCart();
		response.sendRedirect("/Store/jsp/cart.jsp");
		return null;
	}
}
