package com.Blog_Server.Dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.Blog_Server.Dao.ly.DBHelper;
import com.Blog_Server.domain.Columns;
import com.Blog_Server.domain.IndexShowBeen;
import com.Blog_Server.domain.User;
import com.Blog_Server.domain.User_login_record;

import utils.TxQueryRunner;

public class IndexDao {
	private QueryRunner qr = new TxQueryRunner();
	
//	public IndexShowBeen getmas(Long loginUserid) {
//		String sql1="select count(*) from columns where parentId=?";
//		String sql2="select * from User_login_record where userid=?";
//		User_login_record ulr=new User_login_record();
//		ulr=DBHelper.unique(sql2, User_login_record.class, loginUserid);
//		int col=DBHelper.update(sql1, loginUserid);
//		System.out.println("文章条数："+col);
//		IndexShowBeen isb=new IndexShowBeen();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		isb.setLastLastTime(sdf.format(ulr.getTime()));
//		isb.setArticleCount(String.valueOf(col));
//		return isb;
//	}

	/**
	 * 查文章条数
	 * @return
	 * @throws SQLException 
	 */
	public int getArticleCount() throws SQLException {
		String sql ="select count(*) from article";
		Number num = qr.query(sql, new ScalarHandler<Number>());
		return num.intValue();
	}
	
	/**
	 * 查询评论条数
	 * @return
	 * @throws SQLException 
	 */
	public int getCommentCount() throws SQLException {
		String sql="select count(*) from comment";
		Number num=qr.query(sql, new ScalarHandler<Number>());
		return num.intValue();
	}

	/**
	 * 查询最后一次登录时间
	 * @return
	 * @throws SQLException 
	 */
	public Timestamp getLastLastTime(User loginUser) throws SQLException {
		Long id = loginUser.getId();
		String sql = "select MAX(time) from user_login_record where userId=?";
		Object param = loginUser.getId();
		return qr.query(sql, new ScalarHandler<Timestamp>(), param);
	}
	/**
	 * 查询友情链接条数
	 * @return
	 * @throws SQLException 
	 */
	public int getFlinkCount() throws SQLException {
		String sql = "select count(*) from flink";
		Number num=qr.query(sql, new ScalarHandler<Number>());
		return num.intValue();
	}

	/**
	 * 获取当前用户登录次数
	 * @param loginUser
	 * @return
	 * @throws SQLException 
	 */
	public int getLoginCount(User loginUser) throws SQLException {
		String sql = "select count(*) from user_login_record where userId=?";
		Object param=loginUser.getId();
		Number num= qr.query(sql, new ScalarHandler<Number>(), param);
		return num.intValue();
	}
	/**
	 * 查询当前用户最后一次登录IP
	 * @param loginUser
	 * @return
	 * @throws SQLException 
	 */
	public String getLastLastIP(User loginUser) throws SQLException {
		String sql = "select loginIp from user_login_record where time=(select MAX(time) from user_login_record where userId=?)";
		Object param=loginUser.getId();
		String o=qr.query(sql, new ScalarHandler<String>(), param); 
		//o.toString();  //java.lang.Object@2530c12  java.lang.Object  类名+哈希值
		return o;
	}
	/**
	 * 查询管理员个数
	 * @return
	 * @throws SQLException 
	 */
	public int getAdminCount() throws SQLException {
		String sql ="select count(*) from user";
		Number num=qr.query(sql,new ScalarHandler<Number>());
		return num.intValue();
	}

	

}
