package com.skyline.store.web.base;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ͻ����ύ����������method��Ӧ��ֵ
		String md = request.getParameter("method");
		if(md==null || "".equals(md)|| md.trim().equals("")) {
			md="execute";
		}
		//����path�������洢�������֮����Ҫת����·��
		String path = null;
		//�ж�md�е����ݽ��б��ι���ѡ��
		
		Class clazz = this.getClass();
		try {
			Method method = clazz.getMethod(md, HttpServletRequest.class, HttpServletResponse.class);
			if(md!=null) {
				path = (String)method.invoke(this,  request, response);
				if(path!=null) {
					request.getRequestDispatcher(path).forward(request, response);
			}
			}
		}catch(NoSuchMethodException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}
