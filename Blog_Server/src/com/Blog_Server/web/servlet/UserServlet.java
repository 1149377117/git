package com.Blog_Server.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Blog_Server.Exception.IndexException;
import com.Blog_Server.Exception.UserLoginException;
import com.Blog_Server.domain.IndexShowBeen;
import com.Blog_Server.domain.User;
import com.Blog_Server.domain.User_login_record;
import com.Blog_Server.service.IndexService;
import com.Blog_Server.service.UserService;
import com.alibaba.fastjson.JSON;

import util.DateUtils;
import utils.FormUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	// 建立service联系
	private UserService userService = new UserService();
	private IndexService indexService = new IndexService();

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws IndexException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将表单传入信息封装进user对象
		User u = new User();
		u.setAccount(request.getParameter("uusername"));
		u.setPwd(request.getParameter("upassword"));
		try {
			// 将user对象传到userService处理业务，可能有异常
			User user = userService.login(u);
			/*
			 * 登录成功向登录日志表中插入数据
			 */
			User_login_record ulr = getUserLoginRecord(request, user);
			userService.addLog(ulr);
			// 将当前登录的用户对象存入session中
			session.setAttribute("loginUser", user);

			IndexShowBeen isb = getIndexShowBeen(request, user);

			session.setAttribute("IndexShowBeen", isb);// 将主页信息对象存在session里

			response.sendRedirect(request.getContextPath() + "/index.jsp");
			// request.getRequestDispatcher("/index.jsp").forward(request,
			// response);
		} catch (UserLoginException e) {
			// 回显错误信息的参数
			// "<script>$(document).ready(function(){alert("+e.getMessage()+");});</script>"
			// "<font color='red'>* "+e.getMessage()+"</font>"
			request.setAttribute("request_msg",
					"<script>$(document).ready(function(){alert(" + "'" + e.getMessage() + "'" + ");});</script>");
			// 转发到登录页面再次登录
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); // 获取转发器对象
			rd.forward(request, response); // 调用转发器中的转发方法
		}
	}

	public void query(HttpServletRequest request, HttpServletResponse response) {
		// User queryuser=new User();
		// queryuser=userService.findUser();
	}
	/**
	 * 获取选择ID
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			
			response.getWriter().append(JSON.toJSONString(userService.findById(request.getParameter("id"))));
		} catch (UserLoginException e) {
			request.setAttribute("msg", 
					"<script>$(document).ready(function(){alert(" + "'" + e.getMessage() + "'" + ");});</script>");
		}
	}
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		User user = FormUtils.toBean(request, User.class);
		try {
			userService.updateuser(user);
		} catch (UserLoginException e) {
			request.setAttribute("msg", 
					"<script>$(document).ready(function(){alert(" + "'" + e.getMessage() + "'" + ");});</script>");
		}
		findUserList(request, response);
	}
	public void findUserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = null;
		try {
			userList = userService.getUserList();
			session.setAttribute("userList", userList);
			response.sendRedirect(request.getContextPath() + "/manage-user.jsp");
		} catch (UserLoginException e) {
			request.setAttribute("msg",
					"<script>$(document).ready(function(){alert(" + "'" + e.getMessage() + "'" + ");});</script>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		// request.getServletContext().setAttribute("userList", userList);
	}

	/**
	 * 获取User_login_record对象
	 * 
	 * @param request
	 * @return
	 */
	public User_login_record getUserLoginRecord(HttpServletRequest request, User loginUser) {
		User_login_record ulr = new User_login_record();
		ulr.setLoginlp(request.getRemoteAddr());// 设置当前登录IP
		ulr.setTime(new Timestamp(new Date().getTime()));// 设置当前登录时间
		ulr.setUserid(loginUser.getId());// 设置当前登录用户ID
		return ulr;
	}

	/**
	 * 获取IndexShowBeen对象
	 * 
	 * @return
	 */
	public IndexShowBeen getIndexShowBeen(HttpServletRequest request, User loginUser) {
		IndexShowBeen isb = new IndexShowBeen();// 创建一个IndexShowBeen对象
		// 获取浏览器
		String agent = request.getHeader("User-Agent");// 获取agent请求头 ..;..;..;
		StringTokenizer st = new StringTokenizer(agent, ";");
		String browser = null; // 浏览器名
		if (agent.toLowerCase().contains("chrome")) { // 代理中如果包含chrome就是谷歌
			browser = "谷歌";
		} else if (agent.toLowerCase().contains("firefox")) { // 代理中如果包含firefox就是火狐
			browser = "火狐";
		} else if (agent.toLowerCase().contains("msie")) { // 代理中如果包含msie就是IE
			browser = "IE";
		} else {
			browser = "未识别的浏览器"; // 不是三种浏览器
		}
		st.nextToken();// 跳过第一个";" 第一个没用
		String os = st.nextToken(); // 获取操作系统

		// 设置不需要数据库的属性
		isb.setVisitorCount((String) request.getServletContext().getAttribute("visitorCount"));//
		isb.setServerSoft("Apache/2.4.10 (Win32) OpenSSL/1.0.1i mod_fcgid/2.3.9");//
		isb.setBrowser(browser);//
		isb.setVersionsOfPHP("5.6.1");//
		isb.setOperatingSystem(os);
		isb.setModeOfPHP("CGT-FCGT");
		isb.setVersionsOfProcedore("YlsatCMS 1.0");
		isb.setVersionsOfMySql("5.5.40");
		isb.setFileUpload("可以上传文件");
		isb.setEncode("UTF-8");
		isb.setNowTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		isb.setVersionsHave("www.hyycinfo.com");
		isb.setLoadTime("1.09s");

		// 下面的属性设置需要用到数据库
		try {
			isb = indexService.getIndexShowBeen(isb, loginUser);
		} catch (UserLoginException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return isb;
	}
}
