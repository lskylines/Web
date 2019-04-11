package com.skyline.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyline.store.dao.ProductDao;
import com.skyline.store.dao.daoImpl.ProductDaoImpl;
import com.skyline.store.domain.Product;
import com.skyline.store.web.base.BaseServlet;

public class IndexServlet extends BaseServlet {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//����ҵ���
		ProductDao dao = new ProductDaoImpl();
		List<Product>list01 =  dao.findHots();		//������Ʒ
		List<Product>list02 = dao. findNews();			//������Ʒ 
		request.setAttribute("list01",  list01);
		request.setAttribute("list02",  list02);
		return "/jsp/index.jsp";
	}
}
