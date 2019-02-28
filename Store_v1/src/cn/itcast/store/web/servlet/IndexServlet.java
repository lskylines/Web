package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//调用业务层的功能，获取全部信息，返回集合
//		CategoryService categoryService = new CategoryServiceImpl();
//		List<Category> list = categoryService.getAllCats();
		
		//返回的集合放到request中
//		request.setAttribute("allCats", list);
		//转发到真实首页
		
		
		//调用业务层查询最新，最热商品，返回集合
		ProductService productService = new ProductServiceImpl();
		List<Product> list01 = productService.findNews() ;
		List<Product> list02 = productService.findHots();
		System.out.println(list01);
		System.out.println(list02);
		//将2个集合传到request中
		request.setAttribute("hots", list02);
		request.setAttribute("news", list01);
		
		return "/jsp/index.jsp";
	}
}
