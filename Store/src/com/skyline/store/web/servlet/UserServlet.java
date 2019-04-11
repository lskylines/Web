package com.skyline.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.skyline.store.domain.User;
import com.skyline.store.service.UserService;
import com.skyline.store.service.serviceImpl.UserServiceImpl;
import com.skyline.store.utils.MyBeanUtils;
import com.skyline.store.utils.UUIDUtils;
import com.skyline.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
    public String registerUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	return "/jsp/register.jsp";
    }
    public String loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	return "/jsp/login.jsp";
    }
    
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String[]> map = request.getParameterMap();
    	//时间格式的转换
    	DateConverter dt = new DateConverter();
    	dt.setPattern("yyyy-MM-dd");
    	ConvertUtils.register(dt, java.util.Date.class);
    	User userBean = new User();
    	userBean.setUid(UUIDUtils.getId());
    	userBean.setState(0);
    	userBean.setCode(UUIDUtils.getCode());
    	try {
    		//使用Beanutils填充对象
			
    		BeanUtils.populate(userBean, map);
			
			System.out.println(userBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//调用业务层注册功能
    	
    	UserService userService = new UserServiceImpl();
    	try {
			userService.userRegist(userBean);
			request.setAttribute("msg", "注册成功，请激活");
		} catch (SQLException e) {
			request.setAttribute("msg",  "注册失败");
			e.printStackTrace();
		}
    	
    	return "/jsp/info2.jsp";
    }
    
    
    //实现用户登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	User user = new User();
    	//对象数据填充
    	MyBeanUtils.populate(user, request.getParameterMap());
    	//调用业务层
    	UserService service = new UserServiceImpl();
    	User user02 = null;
    	try {
    		//登录成功，用户信息保存到session中
    		user02 = service.login(user);
    		request.getSession().setAttribute("loginuser", user02);
    		response.sendRedirect("/Store/index.jsp");
    		return null;
    	}catch(Exception e) {
    		//登录失败,失败信息保存到request中
    		String msg = e.getMessage();
    		request.setAttribute("msg", msg);
    		return "/jsp/login.jsp";
    		
    	}
    }
    
    //用户退出
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().invalidate();
    	response.sendRedirect("/Store/index.jsp");
    	return null;
    }
    
    

}
