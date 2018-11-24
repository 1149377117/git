package com.Blog_Server.domain;

import java.sql.Timestamp;
/**
 * 用户
 * @author Administrator
 *
 */
public class User extends Userparent implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String account;
	private String pwd;
	private String tel;
	private String createBy;
	private Timestamp createDate;
	private String status;
	private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", account=" + account + ", pwd=" + pwd + ", tel=" + tel
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", status=" + status + ", type=" + type
				+ "]";
	}
	public User(Long id, String name, String account, String pwd, String tel, String createBy, Timestamp createDate,
			String status, String type) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.pwd = pwd;
		this.tel = tel;
		this.createBy = createBy;
		this.createDate = createDate;
		this.status = status;
		this.type = type;
	}
	public User() {
		super();
	}
	
}
