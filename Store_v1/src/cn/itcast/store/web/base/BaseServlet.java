package cn.itcast.store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet  {
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		//获取客户端提交到服务器的method对应的值
				String md = request.getParameter("method");
				if(md==null || "".equals(md)|| md.trim().equals("")) {
					md="execute";
				}
				//定义path变量来存储功能完成之后需要转发的路径
				String path = null;
				//判断md中的内容进行本次功能选择
				
				Class clazz = this.getClass();
				try {
					Method method = clazz.getMethod(md, HttpServletRequest.class, HttpServletResponse.class);
					if(md!=null) {
						path = (String)method.invoke(this,  request, response);
					if(path!=null) {
						request.getRequestDispatcher(path).forward(request, response);
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 	
	}
	
	
	//默认方法
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws  Exception {
		
		return null;
	}
}
