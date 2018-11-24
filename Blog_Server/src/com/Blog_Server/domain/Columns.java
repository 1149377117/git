package com.Blog_Server.domain;

import java.sql.Date;
/**
 * 文章类别
 * @author Administrator
 *
 */
public class Columns implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//
	private String columnName;//栏目名称
	private String aliasName;//栏目别名
	private Long parentId;//父节点
	private String keyWords;//关键字
	private String description;//描述
	private String createBy;//创建人
	private Date createDate;//创建时间
	private Long sort;//排序号
	private String status;//显示/隐藏
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Columns [id=" + id + ", columnName=" + columnName + ", aliasName=" + aliasName + ", parentId="
				+ parentId + ", keyWords=" + keyWords + ", description=" + description + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", sort=" + sort + ", status=" + status + "]";
	}
	public Columns(Long id, String columnName, String aliasName, Long parentId, String keyWords, String description,
			String createBy, Date createDate, Long sort, String status) {
		super();
		this.id = id;
		this.columnName = columnName;
		this.aliasName = aliasName;
		this.parentId = parentId;
		this.keyWords = keyWords;
		this.description = description;
		this.createBy = createBy;
		this.createDate = createDate;
		this.sort = sort;
		this.status = status;
	}
	public Columns() {
		super();
	}
	
}
