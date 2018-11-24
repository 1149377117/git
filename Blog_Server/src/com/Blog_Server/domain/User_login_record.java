package com.Blog_Server.domain;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * 记录用户登录信息
 * @author Administrator
 *
 */
public class User_login_record implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long userid;//用户id+
	private Timestamp time;//用户登录时间+
	private String loginlp;//登录ip+
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getLoginlp() {
		return loginlp;
	}
	public void setLoginlp(String loginlp) {
		this.loginlp = loginlp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User_login_record [id=" + id + ", userid=" + userid + ", time=" + time + ", loginlp=" + loginlp + "]";
	}
	
}
