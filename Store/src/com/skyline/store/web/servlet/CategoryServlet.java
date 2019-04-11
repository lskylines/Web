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
		
		//在jedis获取全部分类信息
		Jedis	jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(jsonStr==null || "".equals(jsonStr)) {
			CategoryService service = new CategoryServiceImpl();
			List<Category> list = service.findAll();
			request.setAttribute("list",  list);
			System.out.println("redis没有数据");
			//对象中转换为json格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			jedis.set("allCats",  jsonStr);
		}else {
			jsonStr = jedis.get("allCats");
			System.out.println("redis有数据");
		}
		//告知浏览器本次响应的数据是json格式
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		return null;
	}
}
