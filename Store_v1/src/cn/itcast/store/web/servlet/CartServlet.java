package cn.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.CarItem;
import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	//从session中获取购物车
	Cart cart = (Cart)request.getSession().getAttribute("cart");
	if(cart==null) {
		cart = new Cart();
		request.getSession().setAttribute("cart", cart);
	}
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		ProductService productService = new ProductServiceImpl();
		Product product = productService.findProductByPid(pid);
		CarItem cartItem = new CarItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		//调用购物车上的方法
		cart.addCartItem(cartItem);
		//重定向到/jsp/cart.jsp页面
		response.sendRedirect("/Store_v1/jsp/cart.jsp");
		return null;

	}
	
	
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pid = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
	
		response.sendRedirect("/Store_v1/jsp/cart.jsp");
		return  null;
	}
	
	//清空购物车
	public String ClearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clearCartItem();
		response.sendRedirect("/Store_v1/jsp/cart.jsp");
		return null;
	}
}
