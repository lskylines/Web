package com.skyline.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.skyline.store.domain.User;

public class PriviledgeFilter implements Filter {
    public PriviledgeFilter() {
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		User user = (User)request.getSession().getAttribute("loginuser");
		if(user!=null) {
			chain.doFilter(request, response);
		}else {
			request.setAttribute("msg", "请用户登录后再去访问");
			request.getRequestDispatcher("/jsp/info2.jsp").forward(req, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
