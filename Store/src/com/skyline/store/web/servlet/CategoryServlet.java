package com.skyline.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skyline.store.domain.Category;
import com.skyline.store.service.CategoryService;
import com.skyline.store.service.serviceImpl.CategoryServiceImpl;
import com.skyline.store.utils.JedisUtils;
import com.skyline.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	public String findCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//��jedis��ȡȫ��������Ϣ
		Jedis	jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(jsonStr==null || "".equals(jsonStr)) {
			CategoryService service = new CategoryServiceImpl();
			List<Category> list = service.findAll();
			request.setAttribute("list",  list);
			System.out.println("redisû������");
			//������ת��Ϊjson��ʽ������
			jsonStr = JSONArray.fromObject(list).toString();
			jedis.set("allCats",  jsonStr);
		}else {
			jsonStr = jedis.get("allCats");
			System.out.println("redis������");
		}
		//��֪�����������Ӧ��������json��ʽ
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		return null;
	}
}
