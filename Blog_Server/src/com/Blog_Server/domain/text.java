package com.Blog_Server.domain;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.Blog_Server.Dao.ly.DBHelper;

import utils.TxQueryRunner;

public class text {
	
	private QueryRunner qr = new TxQueryRunner();
	@Test
	public void fun1() throws SQLException {
		
		String sql = "select * from user";
		List<User> list = null;
		list=qr.query(sql, new BeanListHandler<User>(User.class));
		String[] params=null;
		for(int i=0;i<list.size();i++){
			params[i]=list.get(i).getName();
		}
		String sql1="select count(*) from article where author=?";
		Map<Object,String> map=qr.query(sql1, params, new BeanMapHandler<Object,String>(null));
		System.out.println(map);
	}
	
}
