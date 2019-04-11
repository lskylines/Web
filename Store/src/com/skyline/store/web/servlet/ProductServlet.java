package com.skyline.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.Product;
import com.skyline.store.service.ProductService;
import com.skyline.store.service.serviceImpl.ProductServiceImpl;
import com.skyline.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	public String findProductById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		//调用业务层，根据pid查询数据
		ProductService service = new ProductServiceImpl();
		Product product = service.findByPid(pid);
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("product",  product);
		return "/jsp/product_info.jsp";
	}
	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取cid,num
		String cid = request.getParameter("cid");
		int curNum = Integer.parseInt(request.getParameter("num"));
		//调用业务层，分页方式查询商品
		ProductService productService = new ProductServiceImpl();
		PageModel pm = productService.findProductByCidWithPage(cid, curNum);
		//将pageModel对象放入request
		request.setAttribute("page",  pm);
		return "/jsp/product_list.jsp";
	}
}
