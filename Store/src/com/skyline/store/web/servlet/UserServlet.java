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
    	//ʱ���ʽ��ת��
    	DateConverter dt = new DateConverter();
    	dt.setPattern("yyyy-MM-dd");
    	ConvertUtils.register(dt, java.util.Date.class);
    	User userBean = new User();
    	userBean.setUid(UUIDUtils.getId());
    	userBean.setState(0);
    	userBean.setCode(UUIDUtils.getCode());
    	try {
    		//ʹ��Beanutils������
			
    		BeanUtils.populate(userBean, map);
			
			System.out.println(userBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//����ҵ���ע�Ṧ��
    	
    	UserService userService = new UserServiceImpl();
    	try {
			userService.userRegist(userBean);
			request.setAttribute("msg", "ע��ɹ����뼤��");
		} catch (SQLException e) {
			request.setAttribute("msg",  "ע��ʧ��");
			e.printStackTrace();
		}
    	
    	return "/jsp/info2.jsp";
    }
    
    
    //ʵ���û���¼
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	User user = new User();
    	//�����������
    	MyBeanUtils.populate(user, request.getParameterMap());
    	//����ҵ���
    	UserService service = new UserServiceImpl();
    	User user02 = null;
    	try {
    		//��¼�ɹ����û���Ϣ���浽session��
    		user02 = service.login(user);
    		request.getSession().setAttribute("loginuser", user02);
    		response.sendRedirect("/Store/index.jsp");
    		return null;
    	}catch(Exception e) {
    		//��¼ʧ��,ʧ����Ϣ���浽request��
    		String msg = e.getMessage();
    		request.setAttribute("msg", msg);
    		return "/jsp/login.jsp";
    		
    	}
    }
    
    //�û��˳�
    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().invalidate();
    	response.sendRedirect("/Store/index.jsp");
    	return null;
    }
    
    

}
