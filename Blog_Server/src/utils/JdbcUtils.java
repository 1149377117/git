package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//通过配置文件的默认配置获取到连接池对象   要求必须给出c3p0-config.xml文件
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//事务专用连接  一个线程一个连接对象，处理了多线程并发问题
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * 在连接池中取出一个连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		
		//如果事务专用连接对象不为空，说明现在是开启事务了的状态，那么为了一个事务中使用的是同一个Connection，就直接返回当前事务的专用连接对象
		if(conn!=null) return conn;
		return dataSource.getConnection();
	}
	
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		Connection conn = tl.get();
		
		//如果conn不为null  说明当前已经处在某一事务中
		if(conn!=null) throw new SQLException("已经开启事务了，不要重复开启！");
		
		conn = getConnection();		//从连接池中获取一个连接对象
		conn.setAutoCommit(false);		//开启事务方法
		tl.set(conn);  //把当前线程的Connection对象保存起来
	}
	
	/**
	 * 提交事务
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null) throw new SQLException("还没有开启事务，不能提交");
		conn.commit();
		conn.close();
		tl.remove();  //把当前线程的Connection移除   结束业务
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException 
	 */
	public static void roolbackTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null) throw new SQLException("还没有开启事务，不能回滚");
		conn.rollback();
		conn.close();
		tl.remove();
	}
	
	/**
	 * 释放连接
	 * @param conn
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection conn = tl.get();
		/*
		 * 如果当前事务专用的连接为空   
		 * 	说明connection不是事务专用的连接
		 * 因为现在还没有开启事务
		 */
		if(conn == null) connection.close();
		
		/*
		 * conn 不为null的情况下   说明有事务
		 * 		如果conn!=connection  说明connection不是事务专用连接   就要关闭
		 */
		if(conn != connection) connection.close();
	}
}
