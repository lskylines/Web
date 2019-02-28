 package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.naming.java.javaURLContextFactory;

import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImpl.UserServiceImpl;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	
	//返回用户注册页面地址
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	//返回登录页面地址
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		
		
		MyBeanUtils.populate(user, map);
		System.out.println(user);
//		Set<String> keySet = map.keySet();
//		Iterator<String> iterator = keySet.iterator();
//		while(iterator.hasNext()) {
//			String str = iterator.next();
//			System.out.println(str);
//			String[] strs = map.get(str);
//			for(String string:strs) {
//				System.out.println(string);
//			}
//			System.out.println();
//		}
		
		//调用业务层的实现
		UserService userService = new UserServiceImpl();
		try {
		userService.userRegister(user);
		//注册成功
		request.setAttribute("msg", "用户注册成功");
		
		}catch(Exception e) {
			//注册失败
			request.setAttribute("msg", "用户注册失败,请重新注册!");
		}
		return "/jsp/info.jsp";
	}
	
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户数据
		User user = new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		//user02用于存储用户全部信息
		User user02 = null;
		
		//调用业务层的登录功能
		UserService userService = new UserServiceImpl();
		try {
			user02 = userService.userLogin(user);
			//用户登录成功,将用户信息放入session中
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/Store_v1/index.jsp");
			return  null;
		}catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
	}
	
	public void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清除session
		request.getSession().invalidate();
		//重定向到首页
		response.sendRedirect("/Store_v1/index.jsp");
	}
	
	
	
	
	
	

}
