package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.utils.JedisUtils;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	//用于页面加载完成之后Ajax发送请求过来拿数据
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if(jsonStr==null || "".equals(jsonStr)) {
		
			//调用业务层的全部分类
			CategoryService categoryService = new CategoryServiceImpl();
			List<Category> list =  categoryService.getAllCats();
			//将全部分类转换为JSON格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println(jsonStr);
			//获取的数据放入redis
			jedis.set("allCats", jsonStr);
			System.out.println("redis没有缓存数据");
			//将全部分类信息响应到客户端
			//告诉浏览器本次响应的是JSON数据
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(jsonStr);
		}else {
			//告诉浏览器本次响应的是JSON数据
			System.out.println("redis中有数据");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(jsonStr);
		}
		JedisUtils.closeJedis(jedis);
		return null;
	}
}
