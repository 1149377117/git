package com.Blog_Server.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;

import com.Blog_Server.Dao.ly.DBHelper;
import com.Blog_Server.domain.User;
import com.Blog_Server.domain.User_login_record;

import utils.TxQueryRunner;
/**
 * 
 * @author Administrator
 *
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 连接数据库判断用户是否存在，不存在返回null
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public List<User> isExistUser(User user) throws SQLException {
		String sql = "select * from user where account=?";
		Object param = user.getAccount();  //设置参数
		return qr.query(sql, new BeanListHandler<User>(User.class), param);
	}

	/**
	 * 连接数据库判断密码是否正确，不存在返回null
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public List<User> getUserByPassword(User user) throws SQLException {
		String sql = "select * from user where account=? and pwd=?";
		Object[] params = {user.getAccount(),user.getPwd()};
		return qr.query(sql, new BeanListHandler<User>(User.class), params);
	}

	public void addLOG(User_login_record ulr) throws SQLException {
		String sql="insert into user_login_record values(?,?,?,?)";
		Object[] params = {ulr.getId(),ulr.getUserid(),ulr.getTime(),ulr.getLoginlp()};
		qr.update(sql, params);
		
	}

	public List<User> FindUser() throws SQLException {
		String sql = "select a.*,"+
					"( select count(*) from article b where b.author=a.account) articlecount,"+
					"(select MAX(time) from user_login_record c where c.userid=a.id) userlasttime "+
					"from user a";
		return qr.query(sql, new BeanListHandler<User>(User.class));
	}
	/**
	 * 获取ID
	 * @param parameter 
	 * @return
	 * @throws SQLException 
	 */
	public User findId(String parameter) throws SQLException {
		String sql="select * from user where id=?";
		List<User> list=qr.query(sql,new BeanListHandler<User>(User.class),parameter);
		System.out.println("长度"+list.size());
		if(list==null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	/**
	 * 执行用户数据修改
	 * @param user
	 * @throws SQLException 
	 */
	public void update(User user) throws SQLException {
		String sql="update user set name=?,account=?,tel=? where id=?";
		Object[] params={user.getName(),user.getAccount(),user.getTel(),user.getId()};
		qr.update(sql, params);
	}
}
