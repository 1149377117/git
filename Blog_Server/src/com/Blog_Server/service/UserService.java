package com.Blog_Server.service;

import java.sql.SQLException;
import java.util.List;

import com.Blog_Server.Dao.UserDao;
import com.Blog_Server.Exception.UserLoginException;
import com.Blog_Server.domain.User;
import com.Blog_Server.domain.User_login_record;
import com.mysql.jdbc.JDBC4CallableStatement;

import utils.JdbcUtils;

public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * 登录业务处理
	 * 
	 * @param user
	 * @throws UserLoginException
	 */
	public User login(User user) throws UserLoginException {
		try {
			List<User> list1 = userDao.isExistUser(user);
			// 将user对象传到数据层判断用户名是否正确
			if (list1 == null || list1.size() < 1)
				throw new UserLoginException("用户名不存在");
			List<User> list2 = userDao.getUserByPassword(user);
			if (list2 == null || list2.size() < 1)
				throw new UserLoginException("密码错误");

			return list2.get(0);
		} catch (SQLException e1) {
			throw new UserLoginException("系统繁忙，请稍后再试");
		}

	}

	/**
	 * 添加登录记录
	 * 
	 * @param ulr
	 * @throws UserLoginException
	 */
	public void addLog(User_login_record ulr) throws UserLoginException {
		try {
			JdbcUtils.beginTransaction(); // 开启事务
			userDao.addLOG(ulr);

			JdbcUtils.commitTransaction(); // 提交事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction(); // 回滚
			} catch (SQLException e1) {
				throw new UserLoginException("回滚失败");
			}
		}
	}

	public List<User> getUserList() throws UserLoginException {
		try {
			List<User> list = userDao.FindUser();
			return list;
		} catch (SQLException e) {
			throw new UserLoginException("系统繁忙");
		}
	}

	public User findUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取修改用户ID
	 * 
	 * @param parameter
	 * @return
	 * @throws UserLoginException
	 */
	public User findById(String parameter) throws UserLoginException {
		User user = null;
		try {
			user = userDao.findId(parameter);
			
			return user;
		} catch (SQLException e) {
			throw new UserLoginException("系统繁忙");
		}
	}

	/**
	 * 修改数据库User信息
	 * 
	 * @param user
	 * @throws UserLoginException
	 */
	public void updateuser(User user) throws UserLoginException {
		System.out.println("需要修改的User"+user);
		if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
			throw new UserLoginException("请填写用户名");
		}
		if (user.getName() == null || user.getName().trim().isEmpty()) {
			throw new UserLoginException("请填写姓名");
		}
		
		
		try {
			JdbcUtils.beginTransaction(); // 开启事务
			userDao.update(user);
			JdbcUtils.commitTransaction(); // 提交事务
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction(); // 回滚
			} catch (SQLException e1) {
				throw new UserLoginException("回滚失败");
			}
		}
	}
}
