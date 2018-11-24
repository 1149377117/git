package com.Blog_Server.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns={"*.jsp","*.s"})
public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest=(HttpServletRequest)request;
		/**
		 * 实现排除权限访问控制的资源
		 */
		//获取当前访问资源名
		String path = httprequest.getServletPath();//返回访问的资源路径
		//判断是否拦截
		if(path.endsWith("login.jsp") || path.endsWith("user.s")){
			chain.doFilter(request, response);
			return;
		}
		if(httprequest.getSession().getAttribute("loginUser") !=null){
			chain.doFilter(request, response);
		}else{
			request.setAttribute("request_msg", "<script>$(document).ready(function(){alert("+"'请先登录系统'"+");});</script>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
