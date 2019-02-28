package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取商品的PID
		String pid = request.getParameter("pid");
		//根据商品的pid查找商品信息
		ProductService productService = new ProductServiceImpl();
		Product  product = productService.findProductByPid(pid);
		//将商品信息放入request中
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
	
	
	public String findProductByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		ProductService productService = new ProductServiceImpl();
		List<Product> product = productService.findProductByCid(cid);
		request.setAttribute("product", product);
		return "/jsp/product_list.jsp";
	}
	
	

}
