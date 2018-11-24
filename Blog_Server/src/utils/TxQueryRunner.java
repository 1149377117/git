package utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 支持事务的对象。并且处理了多线程安全问题。
 * 这个类中的方法，自己来处理连接的问题
 * 无需外界传递
 * 怎么处理呢？
 * 		通过JdbcUtils.getConnection()得到连接!有可能是事务连接，也可能是普通的连接！
 * 		JdbcUtils.releaseConnection()完成对连接的释放！如果是普通连接，关闭之。
 *
 */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/*
		 * 1.得到连接
		 * 2.执行父类方法
		 * 3.释放连接
		 * 4.返回值
		 */
		Connection conn = JdbcUtils.getConnection();
		int[] result = super.batch(conn,sql, params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, param, rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, params, rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, rsh, params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.query(conn,sql, rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql,param);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		int result = super.update(conn,sql,params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.insert(conn,sql, rsh);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.insert(conn,sql, rsh, params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		T result = super.insertBatch(conn,sql, rsh, params);
		JdbcUtils.releaseConnection(conn);
		return result;
	}

}
