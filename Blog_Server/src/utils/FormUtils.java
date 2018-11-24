package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class FormUtils {
	/**
	 * 将普通form表单转换成javaBean对象
	 * @param request	请求对象
	 * @param c	传入转换的类型
	 * @return	返回一个javaBean
	 * @throws Exception
	 */
	public static <T> T toBean(HttpServletRequest request,Class<T> c){
		T t = null;
		try {
			t = (T)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Method[] methods = t.getClass().getDeclaredMethods();
		Vector<Method> v = new Vector<Method>();
		for(int i=0;i<methods.length;i++){
			String methodName = methods[i].getName();
			if(!"set".equals(methodName.substring(0, 3))){
				//这些不是set方法
				continue;
			}
			//剩下的是set方法
			v.add(methods[i]);
		}
		g1:for(Method me:v){
			//将方法名转化为  属性名
			String s  = me.getName().substring(3);   //去掉set的   Defghi
			String first = s.substring(0,1).toLowerCase();   //第一位小写  d
			String attrName = first + s.substring(1);  	//属性名  defghi
			
			//找到属性的类型
			String attrType = me.getParameterTypes()[0].getName();  //java.lang.String
			String params = request.getParameter(attrName);
			if(params == null)
				continue g1;
			try {
				g2:switch(attrType){
				case "java.lang.Integer":
					me.invoke(t, Integer.parseInt(params));
					break g2;
				case "java.lang.String":
					me.invoke(t, params); 
					break g2;
				case "java.lang.Long":
					me.invoke(t, Long.parseLong(params)); 
					break g2;
				case "java.sql.Date":
					DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					me.invoke(t, new java.sql.Date(dateformat.parse(params).getTime())); 
					break g2;
				case "java.sql.Timestamp":
					me.invoke(t,Timestamp.valueOf(params)); 
					break g2;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		return t;
	}
	
	/**
	 * 得到pc
	 * @param request
	 * @return
	 */
	public static int getPc(HttpServletRequest request) {
		String pc = request.getParameter("pc");
		if(pc == null || pc.isEmpty()){
			pc = "1";
		}
		return Integer.parseInt(pc);
	}
}