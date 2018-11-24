package com.Blog_Server.domain;

import java.sql.Date;

/**
 * 猜你喜欢，推荐
 * @author Administrator
 *
 */
public class Flink implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;//友链名字
	private String webUrl;//访问url
	private String linkImg;//连接图标
	private String description;//描述
	private String status;//状态
	private String openWays;//打开方式
	private Long sort;//排序号
	private String createBy;//创建人
	private Date createDate;//创建时间
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
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getLinkImg() {
		return linkImg;
	}
	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpenWays() {
		return openWays;
	}
	public void setOpenWays(String openWays) {
		this.openWays = openWays;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Flink [id=" + id + ", name=" + name + ", webUrl=" + webUrl + ", linkImg=" + linkImg + ", description="
				+ description + ", status=" + status + ", openWays=" + openWays + ", sort=" + sort + ", createBy="
				+ createBy + ", createDate=" + createDate + "]";
	}
	public Flink(Long id, String name, String webUrl, String linkImg, String description, String status,
			String openWays, Long sort, String createBy, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.webUrl = webUrl;
		this.linkImg = linkImg;
		this.description = description;
		this.status = status;
		this.openWays = openWays;
		this.sort = sort;
		this.createBy = createBy;
		this.createDate = createDate;
	}
	public Flink() {
		super();
	}
	
}
