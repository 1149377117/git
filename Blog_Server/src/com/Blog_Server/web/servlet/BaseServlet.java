package com.Blog_Server.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	private String method;

	/**
	 * 所有的请求都会通过这个方法
	 * 所以把一些公共的参数在这里初始化
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.session = request.getSession();
		
		String methodName = request.getParameter("method");  //method=addUser
		Method method = null;
		try {
			//调用当前类的methodName方法
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("你当前类中没有:"+methodName+"() 这个方法",e);
		} 
		
		method.setAccessible(true);
		try {
			method.invoke(this, request,response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

}
