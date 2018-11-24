package com.Blog_Server.Dao.ly;

import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 数据库操作助手类，实现上下文监听器接口，可加载数据库参数
 * 
 * @author 廖彦
 *
 */
public class DBHelper {
	public static String URL = "jdbc:mysql://127.0.0.1/blog";
	public static String USR = "root";
	public static String PWD = "a";
	public static String DRV = "com.mysql.jdbc.Driver";

	static {
		init();
	}

	private static void init() {
		try {
			System.out.println("数据库URL??" + URL);
			Class.forName(DRV);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}

	}

	public static Connection getCon() {
		try {
			return DriverManager.getConnection(URL, USR, PWD);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static int update1(String sql, Object... paramArray) throws SQLException {
		Connection con = getCon();
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql);
		for (int i = 0; i < paramArray.length; i++) {
			ps.setObject(i + 1, paramArray[i]);
		}
		int count = ps.executeUpdate();
		System.out.println(sql);
		/*for (Object o : paramArray) {
			System.out.print(o + ",");
		}*/
		con.close();
		return count;
		
		
		
	}
	public static int update(String sql, Object... params) {

		Connection con = getCon();
		PreparedStatement pstm = null;
		try {
			System.out.println("SQL:" + sql);
			pstm = con.prepareStatement(sql);
			doParams(pstm, params);
			int rows = pstm.executeUpdate();
			System.out.println("update rows " + rows);
			return rows;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
	}

	@SuppressWarnings("unchecked")
	private static void doParams(PreparedStatement pstm, Object... params) {
		try {
			int i = 1;
			for (Object o : params) {
				// 如果元素是一个集合，则取出所有集合里的元素作为参??
				// 不确定的参数类型，直接使用setObject，让jdbc去转??
				if (o != null && o instanceof Collection) {
					for (Object p : (Collection<Object>) o) {
						System.out.println("参数" + i + "=" + p);
						pstm.setObject(i++, p);
					}
				} else if (o != null && o.getClass().isArray()) {
					for (Object p : (Object[]) o) {
						System.out.println("参数" + i + "=" + p);
						pstm.setObject(i++, p);
					}
				} else {
					System.out.println("参数" + i + "=" + o);
					pstm.setObject(i++, o);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询结果，返?? 集合类型?? Vector ，元素类型类型也?? Vector 的结果集
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Vector<Vector<Object>> queryForVector(String sql, Object... params) {
		return select(sql, new Vector<Vector<Object>>().getClass(), new Vector<Object>().getClass(), params);
	}

	/**
	 * 查询结果，以指定的实例类作为元素类型返回 List 结果??
	 * 
	 * @param sql
	 * @param b
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <B> List<B> select(String sql, Class<B> b, Object... params) {
		return (List<B>) select(sql, new ArrayList<B>().getClass(), b, params);
	}

	/**
	 * 查询结果，以Map作为元素类型返回 List 结果??
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> select(String sql, Object... params) {
		return (List<Map<String, Object>>) select(sql, new ArrayList<HashMap<String, Object>>().getClass(),
				new HashMap<String, Object>().getClass(), params);
	}

	/**
	 * @param sql
	 *            执行的查询语??
	 * @param c
	 *            返回集合的类型，如：ArrayList、Vector
	 * @param b
	 *            返回元素的类型，如：HashMap、Vector、ArrayList、实体类
	 * @param params
	 *            参数：可变数组参数，没有参数则不??
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <C extends Collection<B>, B> C select(String sql, Class<C> c, Class<B> b, Object... params) {
		Collection<B> list;
		Connection con = getCon();
		ResultSet rs;
		PreparedStatement pstmt;

		try {
			System.out.println("SQL:" + sql);
			pstmt = con.prepareStatement(sql);
			doParams(pstmt, params);
			rs = pstmt.executeQuery();
			list = toList(rs, c, b);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
		System.out.println("select rows " + list.size());
		return (C) list;
	}

	/**
	 * insert 方法，新增完成后返回自增主键值，注意：该方法不支持批?? insert
	 * 
	 * @param sql
	 * @param params
	 * @return 返回第一个自增列的???
	 */
	@SuppressWarnings("unchecked")
	public static List<List<Object>> insert(String sql, Object... params) {
		Connection con = getCon();
		PreparedStatement pstm = null;
		try {
			System.out.println("SQL:" + sql);
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			doParams(pstm, params);
			pstm.executeUpdate();
			// 获取自增列???结果集
			ResultSet rs = pstm.getGeneratedKeys();
			return toList(rs, new ArrayList<ArrayList<Object>>().getClass(), new ArrayList<Object>().getClass());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(con);
		}
	}

	/**
	 * 构建 where 后面?? col1=? and col2=? 语句??
	 * 
	 * @param entity
	 * @param paramList
	 * @param containColumns
	 * @return
	 */
	public static String buildWhere(Object entity, List<Object> paramList, String... containColumns) {
		StringBuilder sb = new StringBuilder();
		if (entity != null) {
			for (Field f : BeanUtils.getAllFields(entity.getClass())) {
				// 包含下划线的属???是操作属???
				if (f.getName().contains("_") == false
						&& (containColumns.length == 0 || BeanUtils.contains(f.getName(), containColumns))) {
					String condition = buildCondition(entity, paramList, f);
					sb.append(condition);
				}
			}
		}
		return sb.length() == 0 ? " 1=1" : sb.toString().substring(4);
	}

	private static String buildCondition(Object entity, List<Object> paramList, Field field) {
		field.setAccessible(true);
		Object value = null;
		try {
			value = field.get(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String expression = (String) BeanUtils.getValue(entity, field.getName() + "_expression");
		String operator = (String) BeanUtils.getValue(entity, field.getName() + "_operator");
		String condition = " and ";
		if (expression == null) {
			if (operator != null) {
				operator = " " + operator;
			} else if (value != null) {
				operator = " =";
			} else {
				return "";
			}
			String columnName = castDBName(field.getName());
			condition += columnName + operator + " ?";
			paramList.add(value);
		} else {
			String regex = "(\\$\\{([\\w\\.]+)\\})";
			List<String[]> groups = StringUtils.getGroupList(expression, regex);
			expression = StringUtils.replaceAll(expression, regex, "?");
			for (String[] group : groups) {
				Object v = BeanUtils.getValue(entity, group[1]);
				paramList.add(v);
			}
			condition += expression + " ";
		}

		return condition;
	}

	/**
	 * 构建 update 语句后面?? set col1=?,col2=?,col3=? where id=? 语句??
	 * 
	 * @param entity
	 * @param paramList
	 * @param keyColumns
	 * @return
	 */
	public static String buildUpdate(Object entity, List<Object> paramList, String... keyColumns) {
		Class<?> cls = entity.getClass();
		String tablename = castDBName(cls.getSimpleName());
		StringBuilder sb = new StringBuilder("UPDATE " + tablename + " SET ");
		for (Field f : BeanUtils.getAllFields(cls)) {
			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null && BeanUtils.contains(f.getName(), keyColumns) == false) {
				String columnName = castDBName(f.getName());
				sb.append(columnName).append(" = ");
				if (paramList == null) {
					if (value instanceof String) {
						sb.append("'").append(value).append("',");
					} else {
						sb.append(value).append(",");
					}
				} else {
					sb.append("?,");
					paramList.add(value);
				}
			}
		}
		String sql = sb.substring(0, sb.length() - 1).toString();
		sql += " where " + buildWhere(entity, paramList, keyColumns);
		return sql;
	}

	/**
	 * 构建 insert 语句后面?? (col1,col2,col3) values (?,?,?) 语句??
	 * 
	 * @param entity
	 * @param paramList
	 * @param containColumns
	 * @return
	 */
	public static String buildInsert(Object entity, List<Object> paramList, String... containColumns) {
		Class<?> cls = entity.getClass();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		for (Field f : BeanUtils.getAllFields(cls)) {
			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null && (containColumns.length == 0 || BeanUtils.contains(f.getName(), containColumns))) {
				String columnName = castDBName(f.getName());
				sb1.append(columnName).append(",");
				if (paramList == null) {
					if (value instanceof String) {
						sb2.append("'").append(value).append("',");
					} else {
						sb2.append(value).append(",");
					}
				} else {
					sb2.append("?,");
					paramList.add(value);
				}
			}
		}
		String tablename = castDBName(cls.getSimpleName());
		String sql = "INSERT INTO " + tablename + " (" + sb1.substring(0, sb1.length() - 1) + ") VALUES ("
				+ sb2.substring(0, sb2.length() - 1) + ")";
		return sql;
	}

	/**
	 * ?? 结果?? ?? toList
	 * 
	 * @param rs
	 * @param c
	 * @param b
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	private static <C extends Collection<B>, B> C toList(ResultSet rs, Class<C> c, Class<B> b)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException,
			InstantiationException {
		Collection<B> list;
		try {
			list = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new RuntimeException(e1);
		}
		Method[] ms = b.getMethods();
		ResultSetMetaData md = rs.getMetaData();

		String[] colnames = new String[md.getColumnCount()];
		for (int i = 0; i < colnames.length; i++) {
			colnames[i] = md.getColumnName(i + 1);
		}

		B t;
		String mname = null;
		String cname = null;

		while (rs.next()) {
			t = (B) b.newInstance();
			/**
			 * 将结果集设置?? Collection 集合??
			 */
			if (t instanceof Collection) {
				Collection<Object> coll = (Collection<Object>) t;
				for (int i = 0; i < colnames.length; i++) {
					coll.add(rs.getObject(colnames[i]));
				}
				/**
				 * 将结果集设置?? Map 集合??
				 */
			} else if (t instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) t;
				for (int i = 0; i < colnames.length; i++) {
					map.put(colnames[i], rs.getObject(colnames[i]));
				}
				/**
				 * 将结果集设置到实体对象中
				 */
			} else {
				for (int i = 0; i < colnames.length; i++) {
					// 空???忽??
					Object value = rs.getObject(colnames[i]);
					if (value == null) {
						continue;
					}
					cname = castJavaName(colnames[i]);
				//	System.out.println(cname + "==" + colnames[i]);
					cname = "set" + cname.substring(0, 1).toUpperCase() + cname.substring(1).toLowerCase();
					if (ms != null && ms.length > 0) {
						for (Method m : ms) {
							mname = m.getName();
							if (cname.equalsIgnoreCase(mname)) {
								Class<?> cls = m.getParameterTypes()[0];
								String clsName = cls.getSimpleName().toLowerCase();
								switch (clsName) {
								case "byte":
									m.invoke(t, rs.getByte(colnames[i]));
									break;
								case "short":
									m.invoke(t, rs.getShort(colnames[i]));
									break;
								case "integer":
									m.invoke(t, rs.getInt(colnames[i]));
									break;
								case "long":
									m.invoke(t, rs.getLong(colnames[i]));
									break;
								case "float":
									m.invoke(t, rs.getFloat(colnames[i]));
									break;
								case "double":
									m.invoke(t, rs.getDouble(colnames[i]));
									break;
								case "string":
									m.invoke(t, rs.getString(colnames[i]));
									break;
								case "boolean":
									m.invoke(t, rs.getBoolean(colnames[i]));
									break;
								case "date":
									m.invoke(t, rs.getDate(colnames[i]));
									break;
								case "timestamp":
									m.invoke(t, rs.getTimestamp(colnames[i]));
									break;
								case "byte[]":
									BufferedInputStream is = null;
									byte[] bytes = null;
									Blob blob = rs.getBlob(colnames[i]);
									try {
										is = new BufferedInputStream(blob.getBinaryStream());
										bytes = new byte[(int) blob.length()];
										is.read(bytes);
									} catch (Exception e) {
										e.printStackTrace();
									}
									m.invoke(t, bytes);
									break;
								default:
									System.out.println("未知类型??" + clsName + "===>" + value + "，听天由命了??");
									m.invoke(t, value);
								}
								break;
							}
						}
					}
				}
			}
			list.add(t);
		}
		return (C) list;
	}

	public static String castJavaName(String columnName) {
		columnName = columnName.toLowerCase();
		StringBuilder sb = new StringBuilder();
		boolean b = false;
		for (Character c : columnName.toCharArray()) {
			if (c.equals('_')) {
				b = true;
				continue;
			} else if (b) {
				sb.append(sb.length() == 0 ? c : Character.toUpperCase(c));
				b = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String castDBName(String fieldName) {
		StringBuilder sb = new StringBuilder();
		for (Character c : fieldName.toCharArray()) {
			if (Character.isUpperCase(c)) {
				if (sb.length() == 0) {
					sb.append(Character.toLowerCase(c));
				} else {
					sb.append("_").append(Character.toLowerCase(c));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将sql语句返回的第??行记录，转换?? Map 对象返回
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String, Object> unique(String sql, Object... params) {
		List<Map<String, Object>> data = DBHelper.select(sql, params);
		if (data == null || data.isEmpty()) {
			return null;
		} else if (data.size() > 1) {
			throw new RuntimeException("返回的结果不是唯????");
		} else {
			return data.get(0);
		}
	}

	/**
	 * 将sql语句返回的第??行记录，转换成指定的实体类（cls）对象返??
	 * 
	 * @param sql
	 * @param cls
	 * @param params
	 * @return
	 */
	public static <T> T unique(String sql, Class<T> cls, Object... params) {
		List<T> data = DBHelper.select(sql, cls, params);
		if (data == null || data.isEmpty()) {
			return null;
		} else if (data.size() > 1) {
			throw new RuntimeException("返回的结果不是唯????");
		} else {
			return data.get(0);
		}
	}

	/**
	 * 返回sql语句返回的第??行记录的指定字段（cnt）的??
	 * 
	 * @param sql
	 * @param column
	 * @param params
	 * @return
	 */
	public static Object unique(String sql, String column, Object... params) {
		List<Map<String, Object>> data = DBHelper.select(sql, params);
		if (data == null || data.isEmpty()) {
			return null;
		} else if (data.size() > 1) {
			throw new RuntimeException("返回的结果不是唯????");
		} else {
			return data.get(0).get(column);
		}
	}

	/**
	 * 
	 * @param sql	查询sql
	 * @param page	页数
	 * @param size	 行数
	 * @param params 查询sql带的参数
	 * @return
	 */
	/*public static Page<Map<String, Object>> pageForMysql(String sql, Integer page, Integer size, Object... params) {
		// 查询分页数据
		String querySql = sql + " limit ?, ?";
		*//**
		 * 注意参数的顺序，分页参数放在后面
		 *//*
		List<Map<String, Object>> data = DBHelper.select(querySql, params, size * (page - 1), size);
		// 查询总的记录??
		String countSql = "select count(*) cnt from (" + sql + ") a";
		// 返回sql语句返回的第??行记录的指定字段（cnt）的??
		long total = (long) unique(countSql, "cnt", params);
		// 返回分页对象
		return new Page<Map<String, Object>>(data, total);
	}*/
	
}
