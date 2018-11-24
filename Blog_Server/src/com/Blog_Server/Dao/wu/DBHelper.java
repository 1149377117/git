package com.Blog_Server.Dao.wu;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class DBHelper {
	/* 初始化context对象 */
	static Context ctx = null;
	
	//静态快，用来加载驱动
	static{
		try {
			Class.forName(Env.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * @throws Exception 
	 */
	public Connection getConnection(){
		Connection con=null;
		try {
			DataSource ds = BasicDataSourceFactory.createDataSource(Env.getInstance());
			con = ds.getConnection();
			
			/*//通过数据连接池去取
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/cludtags");
			//out.print(hello);
			//取出一个连接
		   con = dataSource.getConnection();*/
		   
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}


	/**
	 * 关闭的方法
	 */
	public void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs,CallableStatement cs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		if(cs!=null){
			try {
				cs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}


	private void setValues(PreparedStatement st,List<Object> objs) {
		// 判断是否有参数
		if (objs == null || objs.size() == 0) {
			return;
		}
		try {
			for (int i = 0,len=objs.size(); i < len; i++) {
				if(objs.get(i)!=null){
					String paramType = objs.get(i).getClass().getName(); // 获得参数的类型
					if (Integer.class.getName().equals(paramType)) { // 判断是否是int类型
						st.setInt(i + 1, (Integer) objs.get(i));
					} else if (Double.class.getName().equals(paramType)) { // 判断是否是dobule类型
						st.setDouble(i + 1, (Double) objs.get(i));
					} else if (String.class.getName().equals(paramType)) { // 判断是否是string类型
						st.setString(i + 1, (String) objs.get(i));
					} else {
						st.setObject(i + 1, objs.get(i));
					}
				}else{
					st.setObject(i + 1,objs.get(i));
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取结果集中每个列的类名
	 * @param rs：结果集
	 * @return
	 */
	private String[] getColumnNames(ResultSet rs){
		String[] colNames=null;
		try {
			ResultSetMetaData md=rs.getMetaData(); //获取结果集的元数据，它反映了结果集的信息
			colNames=new String[md.getColumnCount()];//创建一个数据colnames，用来存放列的名字
			for(int i=0;i<colNames.length;i++){  //将列名保存到colname数组中
				colNames[i]=md.getColumnName(i+1).toLowerCase();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return colNames;
	}


	/**
	 * 增删改
	 * @param sql：sql语句集合，里面可以加？
	 * @param params：表示?对应的参数值的集合
	 * @return int:返回的值。成功>0，失败<=0
	 */
	public int update(List<String> sql,List<List<Object>> params){
		int result=0;
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);  //事务处理
			for(int i=0;i<sql.size();i++){
				List<Object> param=params.get(i);
				pstmt=con.prepareStatement(sql.get(i));  //预编译对象
				setValues(pstmt,param);    //设置参数
				result=pstmt.executeUpdate();
			}
			con.commit(); //没有错处执行
		} catch (SQLException e) {
			try {
				con.rollback();  //出错回滚
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			closeAll(con,pstmt,null,null);
		}
		return result;
	}

	/**
	 * 增删改批处理
	 * @param sql：sql语句集合，里面可以加？
	 * @param params：表示?对应的参数值的集合
	 * @return int:返回的值。成功>0，失败<=0
	 */
	public boolean updates(List<String> sqls,List<List<Object>> params){
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		//java中是自动提交事务，所以我们必须先关闭自动提交
		try {
			con.setAutoCommit(false);
			//循环执行sql语句
			for(int i=0;i<sqls.size();i++){
				pstmt=con.prepareStatement(sqls.get(i)); //取出第i条sql语句
				setValues(pstmt, params.get(i)); //取出第i条sql语句对应的参数列表
				pstmt.addBatch();
			}
			//如果所有语句执行后都没有出错，则提交
			pstmt.executeBatch();
			con.commit();
		} catch (SQLException e) {
			//如果执行过程中出错了，则回滚
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			this.closeAll(con, pstmt,null,null);
		}

		return true;
	}

	/**
	 * 单表增删改
	 * @param sql：sql语句集合，里面可以加？
	 * @param params：表示?对应的参数值的集合
	 * @return int:返回的值。成功>0，失败<=0
	 */
	public int update(String sql,List<Object> params){
		int result=0;
		Connection con=getConnection();
		PreparedStatement pstmt=null;	
		try {
			pstmt=con.prepareStatement(sql);  //预编译对象
			setValues(pstmt,params);    //设置参数
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			closeAll(con,pstmt,null,null);
		}
		return result;
	}


	/**
	 * 聚合查询
	 * @param sql：聚合查询语句
	 * @param params：参数列表，用来替换sql中的?（占位符）
	 * @return list:结果集
	 */

	public List<String> uniqueResult(String sql,List<Object> params){
		List<String> list=new ArrayList<String>();
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement(sql);  //预编译对象
			setValues(pstmt,params);   //设置参数
			rs=pstmt.executeQuery();  //执行查询

			ResultSetMetaData md=rs.getMetaData();  //结果集的元数据，它反映了结果集的信息
			int count=md.getColumnCount();    //取出结果集中列的数量

			if(rs.next()){
				for(int i=1;i<=count;i++){
					list.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			closeAll(con,pstmt,rs,null);
		}
		return list;
	}


	/**
	 * 查询
	 * @param <T> 泛型：即你要得到的集合中存的对象的类型
	 * @param sql: 查询语句，可以含有?
	 * @param params: ?所对应的参数值的集合
	 * @param c： 泛型类型所对应的反射对象
	 * @return ：存储了对象的集合
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> List<T> find(String sql,Class<T> c,List<Object> params) {
		List<T> list=new ArrayList<T>(); //要返回的结果的集合
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement(sql); //预编译对象
			setValues(pstmt, params); //设置占位符
			rs=pstmt.executeQuery();  //执行查询语句，得到结果集

			Method[] ms=c.getMethods(); //取出这个反射实例的所有方法
			
			String[] colnames=getColumnNames(rs); //获取结果集中所有列的列名
			T t;
			String mname=null;  //方法名
			String cname=null;  //列名
			String ctypename=null; //类型名

			while(rs.next()){
				t=(T)c.newInstance(); //创建反射类的实例化对象    Product t=(Product)c.newInstance();
				for(int i=0;i<colnames.length;i++){//循环方法名 ,格式为setXXXX或getXXX
					cname=colnames[i]; //取出列名并在前面加上set  setXXX
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					if(ms!=null&&ms.length>0){
						for(Method m:ms){//循环列名
							mname=m.getName(); //取出方法名

							if(cname.equals(mname)&&rs.getObject(colnames[i])!=null){//判断方法名和列名是否一样，相同则激活方法，注入数据      //只要"set"+数据列名.equalsIgnoreCase（方法名），则激活这个方法
								//setXXX(String str); setXXX(int num); 激活对应的方法还必须知道它的数据类型
								ctypename=rs.getObject(colnames[i]).getClass().getName();//获取当前列的类型名

								if("java.lang.Integer".equals(ctypename)){
									m.invoke(t,rs.getInt(colnames[i])); //obj.setXX(xx);
								}else if("java.lang.String".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.math.BigInteger".equals(ctypename)){
									m.invoke(t, rs.getDouble(colnames[i]));
								}else if("java.math.BigDecimal".equals(ctypename)){
									try{
										m.invoke(t, rs.getInt(colnames[i]));
									}catch(Exception e1){
										m.invoke(t, rs.getDouble(colnames[i]));
									}
								}else if("java.sql.Timestamp".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Date".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Time".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("image".equals(ctypename)) {
									m.invoke(t,rs.getBlob(colnames[i]));
								}else{
									m.invoke(t, rs.getString(colnames[i]));
								}
								break;
							}
						}
					}
				}
				list.add(t);
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}finally{
			closeAll(con, pstmt, rs,null);
		}
		return list;
	}

	/**
	 * 查询
	 * @param <T> 泛型：即你要得到的集合中存的对象的类型
	 * @param sql: 查询语句，可以含有?
	 * @param params: ?所对应的参数值的集合
	 * @param c： 泛型类型所对应的反射对象
	 * @return ：存储了对象的集合
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> T findByOne(String sql,Class<T> c,List<Object> params) {
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		T t = null;
		try {
			pstmt=con.prepareStatement(sql); //预编译对象
			setValues(pstmt, params); //设置占位符
			rs=pstmt.executeQuery();  //执行查询语句，得到结果集

			Method[] ms=c.getMethods(); //取出这个反射实例的所有方法
			
			String[] colnames=getColumnNames(rs); //获取结果集中所有列的列名
			String mname=null;  //方法名
			String cname=null;  //列名
			String ctypename=null; //类型名

			while(rs.next()){
				t=(T)c.newInstance(); //创建反射类的实例化对象    Product t=(Product)c.newInstance();
				for(int i=0;i<colnames.length;i++){//循环方法名 ,格式为setXXXX或getXXX
					cname=colnames[i]; //取出列名并在前面加上set  setXXX
					cname="set"+cname.substring(0,1).toUpperCase()+cname.substring(1).toLowerCase();
					if(ms!=null&&ms.length>0){
						for(Method m:ms){//循环列名
							mname=m.getName(); //取出方法名

							if(cname.equals(mname)&&rs.getObject(colnames[i])!=null){//判断方法名和列名是否一样，相同则激活方法，注入数据      //只要"set"+数据列名.equalsIgnoreCase（方法名），则激活这个方法
								//setXXX(String str); setXXX(int num); 激活对应的方法还必须知道它的数据类型
								ctypename=rs.getObject(colnames[i]).getClass().getName();//获取当前列的类型名

								if("java.lang.Integer".equals(ctypename)){
									m.invoke(t,rs.getInt(colnames[i])); //obj.setXX(xx);
								}else if("java.lang.String".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.math.BigInteger".equals(ctypename)){
									m.invoke(t, rs.getDouble(colnames[i]));
								}else if("java.math.BigDecimal".equals(ctypename)){
									try{
										m.invoke(t, rs.getInt(colnames[i]));
									}catch(Exception e1){
										m.invoke(t, rs.getDouble(colnames[i]));
									}
								}else if("java.sql.Timestamp".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Date".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("java.sql.Time".equals(ctypename)){
									m.invoke(t, rs.getString(colnames[i]));
								}else if("image".equals(ctypename)) {
									m.invoke(t,rs.getBlob(colnames[i]));
								}else{
									m.invoke(t, rs.getString(colnames[i]));
								}
								break;
							}
						}
					}
				}
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}finally{
			closeAll(con, pstmt, rs,null);
		}
		return t;
	}
	/**
	 * 查询数据的方法
	 * @param sql：要执行的查询语句
	 * @param params：对应的sql语句中的问号的值
	 * @return：所有满足条件的数据的集合 Map<String,String> key为列名
	 */
	public List<Map<String,String>> find(String sql,List<Object> params){
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		Connection con=getConnection();//获取连接
		PreparedStatement pstmt=null;
		ResultSet rs=null;  
		try {
			//获取连接
			con=this.getConnection();
			//预编译sql语句
			pstmt=con.prepareStatement(sql);

			//给占位符赋值
			setValues(pstmt, params);

			//执行语句并获取返回的结果集
			rs=pstmt.executeQuery();

			//获取返回的结果集中列的信息
			String[] cols=getColumnNames(rs); //获取结果集中所有列的列名

			Map<String,String> map; //用来存放一条记录，以列名为key,对应列的值为value

			//因为封装在Map中，而map我们选用列名为key,对应列的值为value,则我们需要获取所有列的列名
			while(rs.next()){
				map=new HashMap<String,String>();
				for(String col:cols){
					map.put(col,rs.getString(col));
				}
				result.add(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			this.closeAll(con, pstmt, rs,null);
		}
		return result;
	}


	/**
	 * 查询数据的方法
	 * @param sql：要执行的查询语句
	 * @param params：对应的sql语句中的问号的值
	 * @return：所有满足条件的数据的集合 Map<String,String> key为列名
	 */
	public List<List<String>> finds(String sql,List<Object> params){
		List<List<String>> results=new ArrayList<List<String>>(); //存放所有的记录
		List<String> result; //存放一条记录
		Connection con=getConnection();//获取连接
		PreparedStatement pstmt=null;
		ResultSet rs=null;  
		try {
			//获取连接
			con=this.getConnection();
			//预编译sql语句
			pstmt=con.prepareStatement(sql);

			//给占位符赋值
			setValues(pstmt, params);

			//执行语句并获取返回的结果集
			rs=pstmt.executeQuery();

			//获取返回的结果集中列的信息
			ResultSetMetaData rsmd=rs.getMetaData();

			//因为封装在Map中，而map我们选用列名为key,对应列的值为value,则我们需要获取所有列的列名
			while(rs.next()){
				result=new ArrayList<String>();
				for(int i=0,len=rsmd.getColumnCount();i<len;i++){
					result.add(rs.getString(i+1));
				}
				results.add(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			this.closeAll(con, pstmt, rs, null);
		}
		return results;
	}


	/**
	 * 多表查询
	 * @param sql：查询语句
	 * @param params： 查询语句中?所对应的值
	 * @return：结果集，存在一个List表中，用Map一对一的存放
	 * @throws SQLException
	 */
	public List<String> findList(String sql,List<Object> params){
		List<String> result=new ArrayList<String>(); //将结果一次存在list中返回
		Connection con=getConnection();//获取连接
		PreparedStatement pstmt=null;
		ResultSet rs=null;  
		try {
			pstmt=con.prepareStatement(sql);
			setValues(pstmt, params);
			rs=pstmt.executeQuery();
			String[] colnames=getColumnNames(rs); //获取结果集中所有列的列名
			
			while(rs.next()){
				for(int i=0,len=colnames.length;i<len;i++){
					result.add(rs.getString(i+1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			this.closeAll(con, pstmt, rs ,null);
		}
		return result;
	}



	/**
	 * 
	 * @param sql  要执行的sql语句
	 * @param objs 执行sql语句需要的参数
	 * @return  取出数据库的数据, key是字段名或字段别名(小写字母), value应对字段的值
	 */
	public Map<String,String> findMap(String sql,List<Object> objs){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,String> results = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql); // 3.sql执行工具
			setValues(pstmt, objs);
			rs = pstmt.executeQuery(); // 4.执行sql取到返回数据白结果集
			ResultSetMetaData rsmd = rs.getMetaData(); // 元数据; 对象取取到的结果集数据的描述
			int cloumCount = rsmd.getColumnCount();
			if (rs.next()) { // 判断结果集是否还有数据 (数据是一条记录的方式取出)
				results = new HashMap<String,String>();
				for (int i = 1; i <= cloumCount; i++) {
					results.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
				}
			}
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			this.closeAll(con, pstmt, rs ,null);
		}
		return results;
	}


	/**
	 * 存过过程参数设置
	 * @param cst
	 * @param params
	 */
	@SuppressWarnings("unchecked")
	public void setParams(CallableStatement cs,Map<Integer,Object> paramsIn,Map<Integer,String> paramsOut){
		int key=0; //对应的问号的序号
		Object value=null;
		String typename=null;

		String attrType;
		Set keys;  //所有的键
		if(paramsIn!=null&&paramsIn.size()>0){
			keys=paramsIn.keySet();  //取出所有入参的键，即入参对应的问号的序号
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					key=(Integer) iterator.next();
					value=paramsIn.get(key);      //1,88
					attrType=value.getClass().getName();

					//判断值的数据类型
					try {
						if("java.lang.Integer".equals(attrType)){
							cs.setInt(key,(Integer)value);
						}else if("java.lang.String".equals(attrType)){
							cs.setString(key,(String)value);
						}

					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}

		int typeId=0;

		if(paramsOut!=null&&paramsOut.size()>0){
			keys=paramsOut.keySet();  //取出所有入参的键，即入参对应的问号的序号
			if(keys!=null){
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					key=(Integer) iterator.next();
					typename=(String) paramsOut.get(key);      //3,varchar  4, cursor

					//判断值的数据类型
					try {
						 if("int".equals(typename)){
							typeId=Types.INTEGER;
						}else if("double".equals(typename)){
							typeId=Types.NUMERIC;
						}else{
							typeId=Types.VARCHAR;
						}
						cs.registerOutParameter(key,typeId);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

}

